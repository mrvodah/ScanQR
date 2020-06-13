package com.example.scanqr.utils

class AppData {

    var name: String? = null
    var email: String? = null
    var password: String? = null

    companion object {
        private var instance: AppData? = null
        fun g(): AppData {
            if(instance == null) {
                instance = AppData()
            }

            return instance as AppData
        }
    }

}