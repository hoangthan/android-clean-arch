package com.brkr.linagora.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

abstract class BaseFragment : Fragment(), View.OnClickListener {

    /**
     * Layout id which will be used for inflate for fragment.
     */
    abstract var layoutId: Int

    /**
     * This function should be implemented to initialize view, data on views.
     */
    abstract fun initViews()

    /**
     * This function should be implemented to init data observer in viewModels.
     */
    abstract fun initDataObserver()

    /**
     * This function should be implementd to init view listener, which will be triggered click event.
     */
    abstract fun initViewListeners()

    /**
     *
     */
    private val sharedViewModel: SharedViewModel by activityViewModels()

    /**
     * Return inflated layout for views
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    /**
     * Init views and view listener when the layout is created.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDataObserver()
        initViews()
        initViewListeners()
    }

    /**
     * To set loading state is false.
     * This value is observed from BaseActivity
     */
    fun showLoading() {
        sharedViewModel.isLoading.postValue(true)
    }

    /**
     * To set loading state is true.
     * This value is observed from BaseAcitity
     */
    fun hideLoading() {
        sharedViewModel.isLoading.postValue(false)
    }

    /**
     * Show toast from fragment.
     * */
    fun showToast(@StringRes stringId: Int, isLong: Boolean = false) {
        context?.run {
            val duration = if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
            Toast.makeText(context, stringId, duration).show()
        }
    }

    fun showToast(message: String, isLong: Boolean = false) {
        context?.run {
            val duration = if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
            Toast.makeText(context, message, duration).show()
        }
    }
}
