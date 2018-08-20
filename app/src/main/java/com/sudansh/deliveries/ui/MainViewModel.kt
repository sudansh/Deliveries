package com.sudansh.deliveries.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import com.sudansh.deliveries.data.Resource
import com.sudansh.deliveries.repository.DeliveryRepository
import com.sudansh.deliveries.repository.local.db.entity.DeliveryItem
import com.sudansh.deliveries.util.switch

class MainViewModel(val repo: DeliveryRepository) : ViewModel() {
    private val refresh: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading = ObservableBoolean(true)
    val deliveries: LiveData<Resource<List<DeliveryItem>>> =
            refresh.switch { startLoad ->
                repo.getDeliveries(startLoad)
            }

    init {
        refresh.value = false
    }

    fun refresh() {
        refresh.value = true
    }
}