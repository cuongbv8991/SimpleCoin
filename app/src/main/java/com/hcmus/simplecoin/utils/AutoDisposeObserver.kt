package com.hcmus.simplecoin.utils

import androidx.annotation.CallSuper
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class AutoDisposeObserver<T> :Observer<T>{
    private var disposable: Disposable? = null

    @CallSuper
    override fun onComplete() {
        disposable?.dispose()
    }

    @CallSuper
    override fun onSubscribe(d: Disposable) {
        disposable = d
    }
}