package com.willkernel.app.kotlindemo

import android.app.Application
import com.willkernel.app.kotlindemo.data.DelegatesExt

/**
 * Created by willkernel
 * on 2018/3/23.
 */
class App : Application() {
    companion object {
      var instance:App by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}