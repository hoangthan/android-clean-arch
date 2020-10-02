package com.brkr.linagora.presentation.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class SharedViewModel : ViewModel() {

	/**
	 * Loading state live data. This value will be observed by observer.
	 */
	val isLoading = MutableLiveData<Boolean>()

	fun showLoading() {
		isLoading.postValue(true)
	}

	fun hideLoading() {
		isLoading.postValue(false)
	}
}
