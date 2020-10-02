package com.brkr.linagora.presentation.ui.searching

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brkr.linagora.domain.interactor.GetUserUseCase
import com.brkr.linagora.domain.interactor.GetUserUseCase.GetUserParam
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.ensureActive
import org.koin.core.KoinComponent
import org.koin.core.inject

class SearchingViewModel : ViewModel(), KoinComponent {

    private val getUserUseCase: GetUserUseCase by inject()

    fun getUserByUsernameAsync(username: String) = viewModelScope.async(IO) {
        viewModelScope.ensureActive()
        val getUserParam = GetUserParam(username)
        getUserUseCase.execute(getUserParam)
    }
}
