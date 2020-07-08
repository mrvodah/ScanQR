package com.example.scanqr.network

import com.squareup.moshi.Json
import java.util.*


class Employee {
    @Json(name = "ID")
    private var iD: Int? = null

    @Json(name = "EmployeeID")
    private var employeeID: Int? = null

    @Json(name = "Type")
    private var type: Int? = null

    @Json(name = "CreatedDate")
    private var createdDate: String? = null

    @Json(name = "EmployeeCode")
    private var employeeCode: String? = null

    @Json(name = "EmployeeName")
    private var employeeName: String? = null

    @Json(name = "EmployeeDepartment")
    private var employeeDepartment: String? = null

    @Json(name = "EmployeePhoneNo")
    private var employeePhoneNo: String? = null

    fun getID(): Int? {
        return iD
    }

    fun setID(iD: Int?) {
        this.iD = iD
    }

    fun getEmployeeID(): Int? {
        return employeeID
    }

    fun setEmployeeID(employeeID: Int?) {
        this.employeeID = employeeID
    }

    fun getType(): Int? {
        return type
    }

    fun setType(type: Int?) {
        this.type = type
    }

    fun getCreatedDate(): String? {
        val calendar: Calendar = Calendar.getInstance()
        val ackwardRipOff = createdDate?.replace("/Date(", "")?.replace(")/", "")
        val timeInMillis = ackwardRipOff?.let { java.lang.Long.valueOf(it) }
        if (timeInMillis != null) {
            calendar.setTimeInMillis(timeInMillis)
        }
        return calendar.getTime().toGMTString()
    }

    fun setCreatedDate(createdDate: String?) {
        this.createdDate = createdDate
    }

    fun getEmployeeCode(): String? {
        return employeeCode
    }

    fun setEmployeeCode(employeeCode: String?) {
        this.employeeCode = employeeCode
    }

    fun getEmployeeName(): String? {
        return employeeName
    }

    fun setEmployeeName(employeeName: String?) {
        this.employeeName = employeeName
    }

    fun getEmployeeDepartment(): String? {
        return employeeDepartment
    }

    fun setEmployeeDepartment(employeeDepartment: String?) {
        this.employeeDepartment = employeeDepartment
    }

    fun getEmployeePhoneNo(): String? {
        return employeePhoneNo
    }

    fun setEmployeePhoneNo(employeePhoneNo: String?) {
        this.employeePhoneNo = employeePhoneNo
    }
}