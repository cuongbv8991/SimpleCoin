package com.hcmus.simplecoin.data.model

import com.google.gson.annotations.SerializedName

data class Coin(
    @SerializedName("privateKey")
    val privateKey: String?,
    @SerializedName("pubKeyHash")
    val pubKeyHash: String?
)