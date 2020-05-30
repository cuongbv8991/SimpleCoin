package com.hcmus.simplecoin.data.service

import com.hcmus.simplecoin.data.model.*
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SCService {
    @GET("new-wallet")
    fun newWallet(): Observable<Response<Coin>>

    @GET("balance")
    fun getBalance(@Query("id") id: String): Observable<Response<Balance>>

    @POST("transaction")
    fun transaction(@Body sendCoinParams: SendCoinParams): Observable<ResponseWithoutData>
}