package com.example.hubco.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.saveo.data.remote.ApiCallMethods
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

/**
 * @AUTHOR Amandeep Singh
 * @date 15/06/2021
 * */
abstract class BaseViewModel<N : BaseViewActor>(private val apiCallMethods: ApiCallMethods) : ViewModel() {

    //region variables
    private val mCompositeDisposable = CompositeDisposable()
    private lateinit var mViewActor: WeakReference<N>
    //endregion

    override fun onCleared() {
        mCompositeDisposable.dispose()
        super.onCleared()

    }


    protected fun addDisposable(disposable: Disposable) {
        mCompositeDisposable.add(disposable)
    }


    /**
     * Get view actor instance
     *
     * @return [N] type of view actor
     */
    fun getViewActor(): N {
        return mViewActor.get().let { it as N }
    }

    /**
     * Set view actor
     *
     * @param viewActor type of [N]
     */
    fun setViewActor(viewActor: N) {
        this.mViewActor = WeakReference(viewActor)
    }

}