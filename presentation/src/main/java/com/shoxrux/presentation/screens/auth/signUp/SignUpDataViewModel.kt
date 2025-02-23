package com.shoxrux.presentation.screens.auth.signUp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.shoxrux.domain.model.user.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel

class SignUpDataViewModel : ViewModel() {

    var userData = mutableStateOf(SignUpModel())
        private set

    fun updateUserData(newData: SignUpModel) {
        userData.value = newData
    }
}