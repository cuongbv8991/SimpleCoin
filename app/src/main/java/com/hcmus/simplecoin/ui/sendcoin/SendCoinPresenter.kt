package com.hcmus.simplecoin.ui.sendcoin

import com.hcmus.simplecoin.BasePresenter
import com.hcmus.simplecoin.data.model.ResponseWithoutData
import com.hcmus.simplecoin.data.model.SendCoinParams
import com.hcmus.simplecoin.data.service.RetrofitClient
import com.hcmus.simplecoin.data.service.SCService
import com.hcmus.simplecoin.utils.AutoDisposeObserver
import com.hcmus.simplecoin.utils.isNetworkError
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SendCoinPresenter : BasePresenter<SendCoinView>() {
    private val scService: SCService = RetrofitClient.scInstance.create(
        SCService::class.java
    )

    fun sendCoin(sendCoinParams: SendCoinParams) {
        scService.transaction(sendCoinParams)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : AutoDisposeObserver<ResponseWithoutData>() {
                override fun onNext(t: ResponseWithoutData) {
                    if (t.code == 100) {
                        view?.onSuccess()
                    }
                }

                override fun onError(e: Throwable) {
                    if (e.isNetworkError()) {
                        view?.showNoNetworkConnection()
                    } else {
                        view?.showError(e.message)
                    }
                }

            })
    }
}