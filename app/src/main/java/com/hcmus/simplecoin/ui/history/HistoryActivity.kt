package com.hcmus.simplecoin.ui.history

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hcmus.simplecoin.R
import com.hcmus.simplecoin.data.model.Transaction
import com.hcmus.simplecoin.utils.CoinManager
import com.hcmus.simplecoin.utils.showShortToast
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity(), HistoryView {

    private val historyPresenter = HistoryPresenter()

    private var historyAdapter: HistoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        historyPresenter.attachView(this)
        historyAdapter = HistoryAdapter()
        rvHistory.adapter = historyAdapter
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvHistory.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        rvHistory.addItemDecoration(dividerItemDecoration)
        CoinManager.pubKeyHash?.let {
            historyPresenter.getTransaction(it)
        }
    }

    override fun onDestroy() {
        historyPresenter.detachView()
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

    override fun onSuccess(transactions: List<Transaction>) {
        if (transactions.isEmpty()) {
            tvNoHistory.visibility = View.VISIBLE
        } else {
            historyAdapter?.setData(transactions)
        }
    }

    companion object {
        fun intentFor(context: Context): Intent {
            return Intent(context, HistoryActivity::class.java)
        }
    }
}
