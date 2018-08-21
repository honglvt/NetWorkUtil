package com.medtap.network.library.ObserverCallBack

interface DesCallBack<T> {
    fun success(any: T)
    fun failed(e: Throwable)
}