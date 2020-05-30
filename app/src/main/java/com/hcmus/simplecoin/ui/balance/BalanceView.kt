package com.hcmus.simplecoin.ui.balance

import com.hcmus.simplecoin.data.model.Balance

interface BalanceView {
    fun showNoNetworkConnection()

    fun showError(message: String?)

    fun onSuccess(balance: Balance)
}