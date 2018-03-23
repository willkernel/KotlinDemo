package com.willkernel.app.kotlindemo.data

/**
 * Created by willkernel
 * on 2018/3/23.
 */

class Person(var name: String, var username: String) {
    /**构造函数函数体*/
    init {
        name = "Lily"
    }

    var nick: String = ""
        get() = field.toUpperCase()
        set(value) {
            field = "Name: $nick"
        }
}
