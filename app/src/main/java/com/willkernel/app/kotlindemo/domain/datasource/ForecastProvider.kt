package com.willkernel.app.kotlindemo.domain.datasource

import com.willkernel.app.kotlindemo.data.ForecastDb
import com.willkernel.app.kotlindemo.data.ForecastServer
import com.willkernel.app.kotlindemo.extensions.firstResult
import com.willkernel.app.kotlindemo.model.ForecastList

/**
 * Created by willkernel
 * on 2018/3/25.
 */
class ForecastProvider(private val source: List<ForecastDataSource> = ForecastProvider.SOURCES) {
    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList = source.firstResult {
        requestSource(it, days, zipCode)
    }

    private fun requestSource(source: ForecastDataSource, days: Int, zipCode: Long): ForecastList? {
        val res = source.requestForecastByZipCode(zipCode, todayTimeSpan())
        return if (res != null && res.size >= days) res else null
    }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS
}