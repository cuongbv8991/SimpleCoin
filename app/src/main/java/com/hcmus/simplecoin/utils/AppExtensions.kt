package com.hcmus.simplecoin.utils

import android.accounts.NetworkErrorException
import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.annotation.StringRes
import com.hcmus.simplecoin.SCApplication
import java.net.SocketTimeoutException

fun isNetworkConnected(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = cm.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}

fun isNetworkConnected(): Boolean =
    isNetworkConnected(SCApplication.instance)

fun Throwable?.isNetworkError(): Boolean {
    return this is SocketTimeoutException || this is NetworkErrorException
}

fun Context.showShortToast(@StringRes msg: Int) =
    Toast.makeText(this, getString(msg), Toast.LENGTH_SHORT).show()

fun Context.showShortToast(msg: String) =
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

fun appContext() = SCApplication.instance.applicationContext
