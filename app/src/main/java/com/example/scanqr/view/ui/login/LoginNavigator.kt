package com.example.carmanager.view.ui.login

interface LoginNavigator {
    fun onErrorEmail(s: String)
    fun onErrorPassword(s: String)
    fun onSuccess(user: Any?)
    fun onFailure(s: String?)
    fun onRegister()

}