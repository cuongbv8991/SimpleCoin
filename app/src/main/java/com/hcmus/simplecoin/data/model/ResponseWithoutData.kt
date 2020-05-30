package com.hcmus.simplecoin.data.model

import com.google.gson.annotations.SerializedName

class ResponseWithoutData(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("message")
    val message: String?
)