package com.example.scanqr.network

import com.google.gson.annotations.SerializedName

data class CheckEntity(
    @SerializedName("EmployeeCode") var EmployeeCode: String,
    @SerializedName("Type") var Type: String
)