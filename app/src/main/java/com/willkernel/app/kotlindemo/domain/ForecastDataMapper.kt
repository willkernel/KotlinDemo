package com.willkernel.app.kotlindemo.domain

import com.willkernel.app.kotlindemo.data.Forecast
import com.willkernel.app.kotlindemo.data.ForecastResult
import com.willkernel.app.kotlindemo.model.ForecastList
import java.text.DateFormat
import java.util.*
import com.willkernel.app.kotlindemo.model.Forecast as ModelForecast

/**
 * Created by willkernel
 * on 2018/3/23.
 */
public class ForecastDataMapper {
    fun convertFromDataModel(result: ForecastResult): ForecastList {
        return ForecastList(result.city.id, result.city.name, result.city.country,
                convertForecastListToDomain(result.list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>)
            : List<ModelForecast> {
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(forecast: Forecast):
            ModelForecast {
        return ModelForecast(forecast.weather[0].id, forecast.dt,
                forecast.weather[0].description, forecast.temp.max.toInt(),
                forecast.temp.min.toInt(), generateIconUrl(forecast.weather[0].icon))
    }

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date * 1000)
    }

    private fun generateIconUrl(iconCode: String): String = "http://openweathermap.org/img/w/$iconCode.png"
}