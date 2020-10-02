package com.brkr.linagora.presentation.ui.searching

import android.view.View
import android.view.inputmethod.EditorInfo
import com.brkr.linagora.R
import com.brkr.linagora.domain.model.User
import com.brkr.linagora.presentation.ui.base.BaseFragment
import com.brkr.linagora.presentation.utils.ui
import com.brkr.linagora.presentation.utils.value
import kotlinx.android.synthetic.main.fragment_searching.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchingFragment : BaseFragment() {

    override var layoutId: Int = R.layout.fragment_searching

    private val viewModel: SearchingViewModel by viewModel()

    override fun initViews() {
        //Pass
    }

    override fun initDataObserver() {
        //Pass
    }

    override fun initViewListeners() {
        edtUsername.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    searchByUsername()
                    true
                }
                else -> false
            }
        }
    }

    private fun searchByUsername() = ui {
        val username = edtUsername.value()
        showLoading()
        val result = viewModel.getUserByUsernameAsync(username).await()
        hideLoading()
        handleSearchingResult(result)
    }

    private fun handleSearchingResult(result: User?) {
        result ?: run {
            showToast(R.string.user_not_found)
            return
        }
    }

    override fun onClick(p0: View?) {
        //Pass
    }
}
