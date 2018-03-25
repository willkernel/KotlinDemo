package com.willkernel.app.kotlindemo.data

import com.willkernel.app.kotlindemo.domain.datasource.ForecastDataSource
import com.willkernel.app.kotlindemo.model.ForecastList

/**
 * Created by willkernel
 * on 2018/3/25.
 */
class ForecastServer(private val dataMapper: ServerDataMapper = ServerDataMapper(),
                      private val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {

    override fun requestDayForecast(id: Long) = throw UnsupportedOperationException()

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastRequest(zipCode.toString()).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }
}