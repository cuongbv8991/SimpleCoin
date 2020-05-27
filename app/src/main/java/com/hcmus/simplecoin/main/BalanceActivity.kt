package com.hcmus.simplecoin.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hcmus.simplecoin.R
import com.hcmus.simplecoin.data.model.Balance
import com.hcmus.simplecoin.utils.CoinManager
import com.hcmus.simplecoin.utils.showShortToast
import kotlinx.android.synthetic.main.activity_main.*

class BalanceActivity : AppCompatActivity(), BalanceView {

    private val balancePresenter = BalancePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        balancePresenter.attachView(this)
        CoinManager.pubKeyHash?.let {
            balancePresenter.getBalance(it)
        }
    }

    override fun onDestroy() {
        balancePresenter.detachView()
        super.onDestroy()
    }

    override fun showNoNetworkConnection() {
        showShortToast(getString(R.string.no_network_connection))
    }

    override fun showError(message: String?) {
        message?.let {
            showShortToast(it)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onSuccess(balance: Balance) {
        tvBalance.text = balance.balance.toString() + " " + getString(R.string.coin)
    }

    companion object {
        fun intentFor(context: Context): Intent {
            return Intent(context, BalanceActivity::class.java)
        }
    }
}
