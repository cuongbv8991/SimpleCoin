package com.hcmus.simplecoin.ui.sendcoin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hcmus.simplecoin.R
import com.hcmus.simplecoin.data.model.SendCoinParams
import com.hcmus.simplecoin.utils.CoinManager
import com.hcmus.simplecoin.utils.showShortToast
import kotlinx.android.synthetic.main.activity_send_coin.*

class SendCoinActivity : AppCompatActivity(), SendCoinView {

    private var keyPubReceive: String? = null

    private val sendCoinPresenter = SendCoinPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_coin)

        keyPubReceive = intent.getStringExtra(KEY_PUB_RECEIVE)

        initView()
        initListener()
    }

    override fun onDestroy() {
        sendCoinPresenter.detachView()
        super.onDestroy()
    }

    private fun initListener() {
        btnSend.setOnClickListener {
            if (CoinManager.privateKey != null && CoinManager.pubKeyHash != null) {
                try {
                    val receiver = etReceiver.text.toString()
                    val amount = etAmount.text.toString().toInt()
                    val message = etMessage.text.toString()
                    val sendCoinParams = SendCoinParams(
                        CoinManager.privateKey!!,
                        CoinManager.pubKeyHash!!,
                        receiver,
                        amount,
                        message
                    )
                    sendCoinPresenter.sendCoin(sendCoinParams)
                } catch (e: NumberFormatException) {
                    //TODO
                }
            }
        }
    }

    private fun initView() {
        sendCoinPresenter.attachView(this)
        etReceiver.setText(keyPubReceive)
    }

    override fun showNoNetworkConnection() {
        showShortToast(getString(R.string.no_network_connection))
    }

    override fun showError(message: String?) {
        message?.let {
            showShortToast(it)
        }
    }

    override fun onSuccess() {
        showShortToast(getString(R.string.transaction_success))
    }

    companion object {
        const val KEY_PUB_RECEIVE = "key_pub_receive"

        fun intentFor(context: Context): Intent {
            return Intent(context, SendCoinActivity::class.java)
        }
    }
}
