package com.hcmus.simplecoin.ui.sendcoin

interface SendCoinView {
    fun showNoNetworkConnection()

    fun showError(message: String?)

    fun onSuccess()
}