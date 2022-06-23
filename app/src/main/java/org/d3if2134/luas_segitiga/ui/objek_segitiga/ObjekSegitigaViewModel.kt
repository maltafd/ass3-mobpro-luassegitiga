package org.d3if2134.luas_segitiga.ui.objek_segitiga

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if2134.luas_segitiga.MainActivity
import org.d3if2134.luas_segitiga.model.ObjekSegitiga
import org.d3if2134.luas_segitiga.network.ApiStatus
import org.d3if2134.luas_segitiga.network.ObjekSegitigaApi
import org.d3if2134.luas_segitiga.network.UpdateWorker
import java.util.concurrent.TimeUnit

class ObjekSegitigaViewModel: ViewModel() {
    private val data = MutableLiveData<List<ObjekSegitiga>>()
    private val status = MutableLiveData<ApiStatus>()
    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try {
                data.postValue(ObjekSegitigaApi.service.getObjekSegitiga())
                status.postValue(ApiStatus.SUCCESS)
            } catch (e: Exception) {
                status.postValue(ApiStatus.FAILED)
            }
        }
    }
    fun getData(): LiveData<List<ObjekSegitiga>> = data

    fun getStatus(): LiveData<ApiStatus> = status

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            MainActivity.CHANNEL_ID,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}