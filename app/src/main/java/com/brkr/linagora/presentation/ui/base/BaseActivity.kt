package com.brkr.linagora.presentation.ui.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

abstract class BaseActivity : AppCompatActivity(), View.OnClickListener {
    /**
     * Layout id is the layout resource of activity.
     */
    abstract var layoutId: Int

    /**
     * Theme id is the theme resource of activity.
     */
    abstract var themeId: Int

    /**
     * Loading view which will be set visible when loading state is true
     */
    lateinit var loadingView: View

    /**
     * Shared baseViewModel is instantiate only one time.
     * It can be shared for fragment, so it is called SharedViewModels.
     */
    private val sharedViewModel: SharedViewModel by viewModels()

    /**
     * This function use for initialize for view.
     * For example, the data in shared preferences or local repository will be loaded to views.
     */
    abstract fun initViews()


    /**
     * Initialize loading view
     */
    abstract fun initLoadingView()

    /**
     * This function use for initialize listener for Views.
     * Any view want to trigger OnClick event, should be declared at this function.
     * For example: button.setOnClickListener(this)
     */
    abstract fun initViewListeners()

    /**
     * This function use for initialize data observer in ViewModel.
     * Views will be updated if has any changes from liveData.
     */
    abstract fun initDataObserver()


    /**
     * Set theme and view layout
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(themeId)
        setContentView(layoutId)
    }

    /**
     * Init view, dataviews, listener, loading state observer.
     * */
    override fun onStart() {
        super.onStart()
        initViews()
        initLoadingView()
        initViewListeners()
        initDataObserver()
        loadingViewObserver()
    }

    /**
     * Observe loading state if it has any changes.
     */
    private fun loadingViewObserver() {
        sharedViewModel.isLoading.observe(this, { isLoading ->
            if (isLoading) showLoading()
            else hideLoading()
        })
    }

    fun showLoading() {
        loadingView.isVisible = true
    }

    fun hideLoading() {
        loadingView.isVisible = false
    }

    /**
     * Use for open activity.
     * Default current activity will be closed if new activity created.
     * Bundle is optional to put value inside.
     */
    fun openActivity(
        className: Class<*>,
        isCloseCurrentActivity: Boolean = true,
        bundle: Bundle? = null
    ) {
        val intent = Intent(this, className)
        if (bundle != null) intent.putExtras(bundle)
        startActivity(intent)
        if (isCloseCurrentActivity) finish()
    }
}
