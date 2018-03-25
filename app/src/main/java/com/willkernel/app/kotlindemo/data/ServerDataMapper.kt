package com.willkernel.app.kotlindemo.data

import com.willkernel.app.kotlindemo.model.ForecastList
import java.util.*
import java.util.concurrent.TimeUnit
import com.willkernel.app.kotlindemo.model.Forecast as ModelForecast

class ServerDataMapper {
    fun convertToDomain(zipCode: Long, result: ForecastResult) = with(result) {
        ForecastList(zipCode, city.name, city.country, convertForecastListToDomain(list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.mapIndexed { index, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(index.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast) = with(forecast) {
        ModelForecast(-1, dt, weather[0].description, temp.max.toInt(),
                temp.min.toInt(), generateIconUrl(weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String) = "http://openweathermap.org/img/w/$iconCode.png"
}