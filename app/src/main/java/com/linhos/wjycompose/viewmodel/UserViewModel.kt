package com.linhos.wjycompose.viewmodel

import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.ViewModel
import com.linhos.wjycompose.model.entity.UserEntity


class UserViewModel() : ViewModel() {

    var userInfo: UserEntity? = null
        private set
    val logged: Boolean
        get() {
            return userInfo != null
        }

    fun login(userName: String, password: String) {
        userInfo = UserEntity(userName, password)
    }
}


val ActivateUserViewModel = compositionLocalOf<UserViewModel> {error("user view model not found!")  }