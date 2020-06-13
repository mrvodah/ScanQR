package com.example.carmanager.view.ui.register

import androidx.lifecycle.ViewModel
import com.example.scanqr.utils.AppData

class RegisterViewModel: ViewModel() {

    private var listener: RegisterNavigator? = null

    var name: String? = null
    var email: String? = null
    var password: String? = null

    fun setNavigator(navigator: RegisterNavigator) {
        this.listener = navigator
    }

    fun onRegisterClick() {
        if(name.isNullOrEmpty()) {
            listener?.onErrorName("Name mustn't empty")
            return
        }

        if(email.isNullOrEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            listener?.onErrorEmail("Enter a valid email address")
            return
        }

        if(password.isNullOrEmpty() || password?.length!! < 5) {
            listener?.onErrorPassword("Password must at least 5 characters")
            return
        }

        register()
    }

    private fun register() {
        AppData.g().name = name
        AppData.g().email = email
        AppData.g().password = password
        listener?.onSuccess("Success")
    }

    fun onLoginClick() {
        listener?.onLogin()
    }
}