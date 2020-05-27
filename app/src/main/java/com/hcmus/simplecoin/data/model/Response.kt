package com.hcmus.simplecoin.data.model

import com.google.gson.annotations.SerializedName

data class Response<T>(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: T?
)