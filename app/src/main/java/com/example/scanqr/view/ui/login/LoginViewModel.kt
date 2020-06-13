package com.example.carmanager.view.ui.login

import androidx.lifecycle.ViewModel
import com.example.scanqr.utils.AppData

class LoginViewModel : ViewModel() {

    private var listener: LoginNavigator? = null

    var email: String? = "admin@gmail.com"
    var password: String? = "admin"

    fun setNavigator(navigator: LoginNavigator) {
        this.listener = navigator
    }

    fun onLoginClick() {

        if (email.isNullOrEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            listener?.onErrorEmail("Enter a valid email address")
            return
        }

        if (password.isNullOrEmpty() || password?.length!! < 5) {
            listener?.onErrorPassword("Password must at least 5 characters")
            return
        }

        login()

    }

    private fun login() {
        if (email == "admin@gmail.com" && password == "admin") {
            AppData.g().email = email
            AppData.g().password = password
            listener?.onSuccess("Success")
        } else {

            if(AppData.g().name == null) {
                listener?.onFailure("Please register account first!")
            } else {
                if (AppData.g().email == email && AppData.g().password == password) {
                    listener?.onSuccess("Success")
                } else {
                    listener?.onFailure("Email or Password is not correct!")
                }
            }
        }

    }

    fun onRegisterClick() {
        listener?.onRegister()
    }

}