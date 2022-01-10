package com.thiru.cleanarchisample.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {
    val compositeDisposable = CompositeDisposable()

    private val loading:MutableLiveData<Boolean> by lazy { MutableLiveData() }
    val loadingLiveData:LiveData<Boolean> by lazy { loading }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}