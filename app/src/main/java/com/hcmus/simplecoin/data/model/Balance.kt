package com.hcmus.simplecoin.data.model

import com.google.gson.annotations.SerializedName

data class Balance(
    @SerializedName("balance")
    val balance: Int?
)