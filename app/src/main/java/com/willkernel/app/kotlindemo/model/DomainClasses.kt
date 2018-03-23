package com.willkernel.app.kotlindemo.model

/**
 * Created by willkernel
 * on 2018/3/23.
 */
data class ForecastList(val city: String, val country: String,
                        val dailyForecast: List<Forecast>) {
    val size: Int get() = dailyForecast.size

    operator fun get(position: Int) = dailyForecast[position]
}

data class Forecast(val date: String, val description: String,
                    val high: Int, val low: Int, val iconUrl: String)