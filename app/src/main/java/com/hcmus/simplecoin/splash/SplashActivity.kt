package com.hcmus.simplecoin.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.hcmus.simplecoin.R
import com.hcmus.simplecoin.createwallet.CreateWalletActivity
import com.hcmus.simplecoin.main.BalanceActivity
import com.hcmus.simplecoin.utils.CoinManager
import kotlin.math.max

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

    private fun postDelayNavigationToCreateWallet() {
        val time = System.currentTimeMillis() - startTime
        val delayMillis = max(SPLASH_SCREEN_DURATION - time, 0)
        handler.postDelayed({
            startActivity(CreateWalletActivity.intentFor(this).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
        }, delayMillis)
    }

    private fun postDelayNavigationToMain() {
        val time = System.currentTimeMillis() - startTime
        val delayMillis = max(SPLASH_SCREEN_DURATION - time, 0)
        handler.postDelayed({
            startActivity(BalanceActivity.intentFor(this).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
        }, delayMillis)
    }

    companion object {
        const val SPLASH_SCREEN_DURATION = 1000L

        fun intentFor(context: Context): Intent {
            return Intent(context, SplashActivity::class.java)
        }
    }
}
