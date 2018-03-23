package com.willkernel.app.kotlindemo.domain

/**
 * Created by willkernel
 * on 2018/3/23.
 */
public interface Command<T> {
    fun execute(): T
}