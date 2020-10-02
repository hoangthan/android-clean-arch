package com.brkr.linagora.presentation.ui.main

import android.os.Bundle
import android.view.View
import com.brkr.linagora.R
import com.brkr.linagora.presentation.ui.base.BaseActivity
import com.brkr.linagora.presentation.ui.searching.SearchingFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override var layoutId = R.layout.activity_main

    override var themeId = R.style.Theme_Linagora

    override fun initViews() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, SearchingFragment())
            .commit()
    }

    override fun initLoadingView() {
        this.loadingView = loading_view
    }

    override fun initViewListeners() {

    }

    override fun initDataObserver() {

    }

    override fun onClick(p0: View?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
