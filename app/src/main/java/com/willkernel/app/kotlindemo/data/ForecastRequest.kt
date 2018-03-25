package com.willkernel.app.kotlindemo.data

import android.util.Log
import com.google.gson.Gson
import java.net.URL

/**
 * Created by willkernel
 * on 2018/3/23.
 */
public class ForecastRequest(val zipCode: String, val gson: Gson = Gson()) {
    companion object {
        //        private val APP_ID = "326719a9a3494ef25968fcb09950a987"
//        private val URL = "http://api.openweathermap.org/data/2.5/weather?appid="
//        private val COMPLETE_URL = "$URL$APP_ID&q="
        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private val URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "${URL}&APPID=${APP_ID}&zip="
    }

    public fun execute(): ForecastResult {
        val forecastJson = URL(COMPLETE_URL + zipCode).readText()
        Log.e(javaClass.simpleName, forecastJson)
        return gson.fromJson(forecastJson, ForecastResult::class.java)
    }
}