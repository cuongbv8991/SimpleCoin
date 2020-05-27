package com.hcmus.simplecoin

import android.app.Application

class SCApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: SCApplication
    }
}