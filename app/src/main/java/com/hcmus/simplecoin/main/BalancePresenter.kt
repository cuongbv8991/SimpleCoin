package com.hcmus.simplecoin.main

import com.hcmus.simplecoin.BasePresenter
import com.hcmus.simplecoin.data.model.Balance
import com.hcmus.simplecoin.data.model.Response
import com.hcmus.simplecoin.data.service.RetrofitClient
import com.hcmus.simplecoin.data.service.SCService
import com.hcmus.simplecoin.utils.AutoDisposeObserver
import com.hcmus.simplecoin.utils.isNetworkError
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BalancePresenter: BasePresenter<BalanceView>() {
    private val scService: SCService = RetrofitClient.scInstance.create(
        SCService::class.java
    )

    fun getBalance(id:String){
        scService.getBalance(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : AutoDisposeObserver<Response<Balance>>() {
                override fun onNext(t: Response<Balance>) {
                    if(t.code==100){
                        t.data?.let {
                            view?.onSuccess(it)

                        }
                    }
                }

                override fun onError(e: Throwable) {
                    if (e.isNetworkError()) {
                        view?.showNoNetworkConnection()
                    } else {
                        view?.showError(e.message)
                    }                }

            })
    }
}