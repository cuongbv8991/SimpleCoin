package com.hcmus.simplecoin.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.hcmus.simplecoin.R
import com.hcmus.simplecoin.utils.CoinManager

class SplashActivity : AppCompatActivity() {

    private var startTime: Long = 0

    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startTime = System.currentTimeMillis()
        CoinManager.readData()
        if (CoinManager.isLogin) {
            postDelayNavigationToMain()
        } else {
            postDelayNavigationToCreateWallet()
        }
    }
}
