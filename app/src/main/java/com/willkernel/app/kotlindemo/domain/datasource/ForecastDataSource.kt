package com.willkernel.app.kotlindemo.domain.datasource

import com.willkernel.app.kotlindemo.model.Forecast
import com.willkernel.app.kotlindemo.model.ForecastList

interface ForecastDataSource {

    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?

    fun requestDayForecast(id: Long): Forecast?

}