package com.willkernel.app.kotlindemo.extensions

import android.content.Context
import android.view.View

val View.ctx: Context
    get() = context