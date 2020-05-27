package com.hcmus.simplecoin.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hcmus.simplecoin.R
import com.hcmus.simplecoin.data.model.Balance

class BalanceActivity : AppCompatActivity(), BalanceView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun showNoNetworkConnection() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(message: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccess(balance: Balance) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun intentFor(context: Context): Intent {
            return Intent(context, BalanceActivity::class.java)
        }
    }
}
