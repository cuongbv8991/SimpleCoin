package com.hcmus.simplecoin

interface Presenter<V> {

    fun attachView(view: V)

    fun detachView()
}