package com.hcmus.simplecoin.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.hcmus.simplecoin.R
import com.hcmus.simplecoin.data.model.Balance
import com.hcmus.simplecoin.receivecoin.ReceiveCoinActivity
import com.hcmus.simplecoin.utils.CoinManager
import com.hcmus.simplecoin.utils.showShortToast
import kotlinx.android.synthetic.main.activity_main.*


class BalanceActivity : AppCompatActivity(), BalanceView {

    private val balancePresenter = BalancePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initListener()
    }

    private fun initView() {
        balancePresenter.attachView(this)
        CoinManager.pubKeyHash?.let {
            balancePresenter.getBalance(it)
        }
    }

    private fun initListener() {
        btnReceive.setOnClickListener {
            startActivity(ReceiveCoinActivity.intentFor(this))
        }

        btnSend.setOnClickListener {
            val integrator = IntentIntegrator(this)
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            //integrator.setPrompt("Scan a barcode")
            integrator.setCameraId(0) // Use a specific camera of the device

            integrator.setBeepEnabled(false)
            integrator.setBarcodeImageEnabled(true)
            integrator.initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result =
            IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Log.d("decode_qr_code", "Cancelled")
            } else {
                Log.d("decode_qr_code", "Scanned: " + result.contents)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
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
