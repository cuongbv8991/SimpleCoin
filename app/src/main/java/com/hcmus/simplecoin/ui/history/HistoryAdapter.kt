package com.hcmus.simplecoin.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hcmus.simplecoin.R
import com.hcmus.simplecoin.data.model.Transaction
import com.hcmus.simplecoin.utils.CoinManager
import kotlinx.android.synthetic.main.transaction_item.view.*

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private val items: MutableList<Transaction> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.transaction_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setData(list: List<Transaction>?) {
        items.clear()
        list?.apply {
            items.addAll(this)
        }
        notifyDataSetChanged()
    }

    fun getItems(): MutableList<Transaction> = items

    inner class HistoryViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {

        private val imgStatus = itemView.imgStatus

        private val tvSender = itemView.tvSender

        private val tvReceiver = itemView.tvReceiver

        private val tvAmount = itemView.tvAmount

        private val tvMessage = itemView.tvMessage

        fun bind(item: Transaction) {
            if (item.sender == CoinManager.pubKeyHash) {
                tvSender.text = itemView.resources.getString(R.string.me)
                imgStatus.setImageResource(R.drawable.ic_up)
            } else {
                tvSender.text = item.sender
            }
            if (item.receiver == CoinManager.pubKeyHash) {
                tvReceiver.text = itemView.resources.getString(R.string.me)
                imgStatus.setImageResource(R.drawable.ic_down)
            } else {
                tvReceiver.text = item.sender
            }
            tvAmount.text = item.amount.toString()
            tvMessage.text = item.message
        }
    }


}