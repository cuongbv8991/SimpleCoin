package com.hcmus.simplecoin.ui.history

import com.hcmus.simplecoin.BasePresenter
import com.hcmus.simplecoin.data.model.Response
import com.hcmus.simplecoin.data.model.Transaction
import com.hcmus.simplecoin.data.service.RetrofitClient
import com.hcmus.simplecoin.data.service.SCService
import com.hcmus.simplecoin.utils.AutoDisposeObserver
import com.hcmus.simplecoin.utils.isNetworkError
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HistoryPresenter: BasePresenter<HistoryView>() {

    private val scService: SCService = RetrofitClient.scInstance.create(
        SCService::class.java
    )

    fun getTransaction(id:String){
        scService.getTransaction(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : AutoDisposeObserver<Response<List<Transaction>>>() {
                override fun onNext(t: Response<List<Transaction>>) {
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