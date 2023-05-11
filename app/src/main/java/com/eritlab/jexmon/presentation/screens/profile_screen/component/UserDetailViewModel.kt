package com.eritlab.jexmon.presentation.screens.profile_screen.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eritlab.jexmon.domain.item.UserDetailItem
import com.eritlab.jexmon.domain.use_case.user_login.UserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(private val userDetailUseCase: UserDetailUseCase):ViewModel(){
    private val _userDetail = MutableStateFlow<UserDetailItem?>(null)
    val userDetail:StateFlow<UserDetailItem?> get() = _userDetail
    init {

    }
    fun getUser(userId:Int){
        viewModelScope.launch {
            try {
                val userDetailToPage = userDetailUseCase(userId)
                _userDetail.value = userDetailToPage
            }catch (_:Exception){}
        }
    }
}