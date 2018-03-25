package com.willkernel.app.kotlindemo.domain

import com.willkernel.app.kotlindemo.data.ForecastRequest
import com.willkernel.app.kotlindemo.model.ForecastList

/**
 * Created by willkernel
 * on 2018/3/23.
 */
class RequestForecastCommand(private val zipCode: String) : Command<ForecastList> {
    companion object {
        val DAYS=7
    }
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}