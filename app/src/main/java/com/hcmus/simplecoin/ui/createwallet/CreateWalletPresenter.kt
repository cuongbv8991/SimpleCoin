package com.hcmus.simplecoin.ui.createwallet

import com.hcmus.simplecoin.BasePresenter
import com.hcmus.simplecoin.data.model.Coin
import com.hcmus.simplecoin.data.model.Response
import com.hcmus.simplecoin.data.service.RetrofitClient
import com.hcmus.simplecoin.data.service.SCService
import com.hcmus.simplecoin.utils.AutoDisposeObserver
import com.hcmus.simplecoin.utils.isNetworkError
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CreateWalletPresenter : BasePresenter<CreateWalletView>() {
    private val scService: SCService = RetrofitClient.scInstance.create(
        SCService::class.java
    )

    fun createWallet() {
        scService.newWallet()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : AutoDisposeObserver<Response<Coin>>() {
                override fun onNext(t: Response<Coin>) {
                    if (t.code == 100) {
                        t.data?.let {
                            view?.onSuccess(t.data)
                        }
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