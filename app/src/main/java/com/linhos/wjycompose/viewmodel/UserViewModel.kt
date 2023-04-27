package com.linhos.wjycompose.viewmodel

import android.content.Context
import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linhos.wjycompose.model.UserInfoManager
import com.linhos.wjycompose.model.entity.UserEntity
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch


class UserViewModel(context: Context) : ViewModel() {
    val userInfoManager = UserInfoManager(context)

    var userInfo: UserEntity? = null
        private set
    val logged: Boolean
        get() {
            return userInfo != null
        }

    init {
        viewModelScope.launch {
            val userName = userInfoManager.userName.firstOrNull()
            userInfo = if (userName?.isNotEmpty() == true) {
                UserEntity(userName)
            } else {
                null
            }
        }
    }

    fun login(userName: String, password: String, onFinish: (Boolean) -> Unit = {}) {
        userInfo = UserEntity(userName)
        if (password == "0124") {
            viewModelScope.launch {
                userInfoManager.save(userName)
                userInfoManager.logged.collect {
                    onFinish(it)
                }
            }
        } else {
            onFinish(false)
        }


    }
}


internal val ActivateUserViewModel =
    compositionLocalOf<UserViewModel> { error("user view model not found!") }