package com.example.carmanager.view.ui.register

interface RegisterNavigator {
    fun onErrorName(s: String)
    fun onErrorEmail(s: String)
    fun onErrorPassword(s: String)
    fun onLogin()
    fun onSuccess(user: Any?)
    fun onFailure(message: String?)
}