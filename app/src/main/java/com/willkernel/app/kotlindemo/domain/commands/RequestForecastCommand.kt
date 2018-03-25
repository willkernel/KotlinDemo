package com.willkernel.app.kotlindemo.domain.commands

import com.willkernel.app.kotlindemo.domain.datasource.ForecastProvider
import com.willkernel.app.kotlindemo.model.ForecastList

/**
 * Created by willkernel
 * on 2018/3/23.
 */
class RequestForecastCommand(private val zipCode: Long,
                             private val provider: ForecastProvider = ForecastProvider()) : Command<ForecastList> {
    //class RequestForecastCommand(private val zipCode: String) : Command<ForecastList> {
    companion object {
        val DAYS = 7
    }

    override fun execute(): ForecastList {
//        val forecastRequest = ForecastRequest(zipCode)
        return provider.requestByZipCode(zipCode, DAYS)
    }
}