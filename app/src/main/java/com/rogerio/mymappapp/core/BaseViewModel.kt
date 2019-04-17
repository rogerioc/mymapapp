package com.rogerio.mymappapp.core

import android.databinding.Observable
import android.databinding.PropertyChangeRegistry
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
/**
  I use this class in my others projects to help manager Rx
 **/

abstract class BaseViewModel : ViewModel(), Observable {
    var compositeDisposable = CompositeDisposable()

    @Transient
    private var mCallbacks: PropertyChangeRegistry? = null

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        synchronized(this) {
            if (mCallbacks == null) {
                mCallbacks = PropertyChangeRegistry()
            }
        }
        mCallbacks?.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        synchronized(this) {
            if (mCallbacks == null) {
                return
            }
        }
        mCallbacks?.remove(callback)
    }

    fun notifyChange() {
        synchronized(this) {
            if (mCallbacks == null) {
                return
            }
        }
        mCallbacks?.notifyCallbacks(this, 0, null)
    }

    fun notifyPropertyChanged(fieldId: Int) {
        synchronized(this) {
            if (mCallbacks == null) {
                return
            }
        }
        mCallbacks?.notifyCallbacks(this, fieldId, null)
    }

    override fun onCleared() {
        super.onCleared()
        mCallbacks?.clear()
        compositeDisposable.clear()
    }
}


fun Disposable.addTo(viewModel: BaseViewModel): Disposable {
    viewModel.compositeDisposable.add(this)
    return this
}
