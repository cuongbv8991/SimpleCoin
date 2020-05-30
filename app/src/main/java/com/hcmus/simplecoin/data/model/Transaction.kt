package com.hcmus.simplecoin.data.model

import com.google.gson.annotations.SerializedName

data class Transaction(
    @SerializedName("sender")
    val sender: String?,
    @SerializedName("receiver")
    val receiver: String?,
    @SerializedName("amount")
    val amount: Int?,
    @SerializedName("message")
    val message: String?
)