package com.example.app

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WebsiteViewModel : ViewModel() {

    private val _data = MutableLiveData<List<Website>>(emptyList())
    val data: LiveData<List<Website>> = _data

    private var sortedAsc = true

    init {

        viewModelScope.launch {
            getWebsites()

        }

    }

    fun toggleSort() {
        Log.d("VM", "togglesort")
        if (sortedAsc) {
            _data.value = _data.value?.sortedBy { it.name }
        } else {
            _data.value = _data.value?.sortedByDescending { it.name }
        }

        sortedAsc = !sortedAsc
    }

    private suspend fun getWebsites() {

        try {
            _data.value = RetrofitClient.websiteAPIService.getWebsites().sortedBy { it.name }
        }
        catch (e: Exception) {
            Log.e("VM", "Error fetching websites: ${e.message}")
            _data.value = emptyList()

        }

    }


}