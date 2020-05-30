package com.hcmus.simplecoin.ui.createwallet

import com.hcmus.simplecoin.data.model.Coin

interface CreateWalletView {
    fun showNoNetworkConnection()

    fun showError(message: String?)

    fun onSuccess(coin: Coin)
}