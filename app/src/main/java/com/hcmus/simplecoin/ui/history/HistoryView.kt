package com.hcmus.simplecoin.ui.history

import com.hcmus.simplecoin.data.model.Transaction

interface HistoryView {
    fun showNoNetworkConnection()

    fun showError(message: String?)

    fun onSuccess(transactions: List<Transaction>)
}