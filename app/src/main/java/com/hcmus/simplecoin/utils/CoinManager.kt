package com.hcmus.simplecoin.utils

import android.content.Context
import android.content.SharedPreferences
import com.hcmus.simplecoin.data.model.Coin

object CoinManager {
    private const val DEFAULT_STRING = ""

    var privateKey: String? = null

    var pubKeyHash: String? = null

    var isLogin: Boolean = false

    private const val COIN_PREF = "coin_prefs"

    private const val PRIVATE_COIN = "private_key"

    private const val PUBKEYHASH = "pub_key_hash"

    private val sharedPreferences: SharedPreferences? =
        appContext().getSharedPreferences(COIN_PREF, Context.MODE_PRIVATE)

    fun readData() {
        sharedPreferences?.let {
            privateKey = it.getString(PRIVATE_COIN, DEFAULT_STRING)
            pubKeyHash = it.getString(PUBKEYHASH, DEFAULT_STRING)
        }
        isLogin = privateKey != DEFAULT_STRING
    }

    fun deleteData() {
        sharedPreferences?.edit()?.let {
            it.putString(PRIVATE_COIN, DEFAULT_STRING)
            it.putString(PUBKEYHASH, DEFAULT_STRING)
            it.apply()
            privateKey = DEFAULT_STRING
            pubKeyHash = DEFAULT_STRING
            isLogin = false
        }
    }

    fun writeData(coin: Coin?) {
        sharedPreferences?.edit()?.let {
            privateKey = coin?.privateKey
            it.putString(PRIVATE_COIN, privateKey)
            pubKeyHash = coin?.pubKeyHash
            it.putString(PUBKEYHASH, pubKeyHash)
            it.apply()
        }
    }
}