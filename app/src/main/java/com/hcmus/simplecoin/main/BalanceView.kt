package com.hcmus.simplecoin.main

import com.hcmus.simplecoin.data.model.Balance
import com.hcmus.simplecoin.data.model.Coin

interface BalanceView {
    fun showNoNetworkConnection()

    fun showError(message: String?)

    fun onSuccess(balance: Balance)
}