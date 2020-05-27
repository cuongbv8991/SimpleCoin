package com.hcmus.simplecoin.createwallet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hcmus.simplecoin.R
import com.hcmus.simplecoin.data.model.Coin
import com.hcmus.simplecoin.main.BalanceActivity
import com.hcmus.simplecoin.utils.CoinManager
import com.hcmus.simplecoin.utils.showShortToast
import kotlinx.android.synthetic.main.activity_create_wallet.*

class CreateWalletActivity : AppCompatActivity(), CreateWalletView {

    private val createWalletPresenter = CreateWalletPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_wallet)

        createWalletPresenter.attachView(this)
        btnCreateWallet.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            createWalletPresenter.createWallet()
        }
    }

    override fun onDestroy() {
        createWalletPresenter.detachView()
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

    override fun onSuccess(coin: Coin) {
        progressBar.visibility = View.GONE
        CoinManager.writeData(coin)
        startActivity(BalanceActivity.intentFor(this))
    }

    companion object {
        fun intentFor(context: Context): Intent {
            return Intent(context, CreateWalletActivity::class.java)
        }
    }
}
