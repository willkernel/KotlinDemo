package com.willkernel.app.kotlindemo.data

import com.willkernel.app.kotlindemo.domain.datasource.ForecastDataSource
import com.willkernel.app.kotlindemo.extensions.*
import com.willkernel.app.kotlindemo.model.ForecastList
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * Created by willkernel
 * on 2018/3/24.
 */
class ForecastDb(val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                 val dataMapper: DbDataMapper = DbDataMapper()) : ForecastDataSource {

    //dailyRequest 是查询语句中 where 的一部分。它是 whereSimple 函
    //数需要的第一个参数，这与我们用一般的helper做的方式很相似
    override fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {
        val dailyRequest = "${DayForecastTable.CITY_ID}=? " +
                "AND ${DayForecastTable.DATE}>=?"
        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList { DayForecast(HashMap(it)) }

//        简化写法
//        val dailyRequest = "${DayForecastTable.CITY_ID} = {id}" + "AND $
//        {DayForecastTable.DATE} >= {date}"
//        val dailyForecast = select(DayForecastTable.NAME)
//                .where(dailyRequest, "id" to zipCode, "date" to date)
//                .parseList { DayForecast(HashMap(it)) }
        val city = select(CityForecastTable.NAME)
                .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
                .parseOpt { CityForecast(HashMap(it), dailyForecast) }

        city?.let { dataMapper.convertToDomain(it) }
    }

    fun saveForecast(forecastList: ForecastList) = forecastDbHelper.use {
        clear(DayForecastTable.NAME)
        clear(CityForecastTable.NAME)
        with(dataMapper.convertFromDomain(forecastList)) {
            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach { insert(DayForecastTable.NAME, *it.map.toVarargArray()) }
        }
    }

    override fun requestDayForecast(id: Long) = forecastDbHelper.use {
        val forecast = select(DayForecastTable.NAME).byId(id)
                .parseOpt { DayForecast(java.util.HashMap(it)) }

        forecast?.let { dataMapper.convertDayToDomain(it) }
    }
}