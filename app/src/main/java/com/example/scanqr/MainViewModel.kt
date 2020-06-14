package com.example.scanqr

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.scanqr.network.CheckEntity
import com.example.scanqr.network.CheckResponse
import com.example.scanqr.network.QrApi
import com.example.scanqr.view.ui.home.QrApiStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel: ViewModel() {

    val viewModelJob = Job()
    val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // The internal MutableLiveData String that stores the most recent status
    private val _status = MutableLiveData<QrApiStatus>()

    // The external immutable LiveData for the status String
    val status: LiveData<QrApiStatus>
        get() = _status

    private val _properties = MutableLiveData<CheckResponse>()

    val properties: LiveData<CheckResponse>
        get() = _properties

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    public fun sendRequest(req: CheckEntity) {
        uiScope.launch {
            val getPropertiesDeferred = QrApi.retrofitService.sendCheck("application/json", req)

            try {
                _status.value = QrApiStatus.LOADING
                val listResult = getPropertiesDeferred.await()
                _status.value = QrApiStatus.DONE
                _properties.value = listResult
            } catch (e: Exception) {
                Log.d("TAG", e.printStackTrace().toString())
                _status.value = QrApiStatus.ERROR
                _properties.value = CheckResponse(false)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}