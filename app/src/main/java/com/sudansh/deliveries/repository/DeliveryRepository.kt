package com.sudansh.deliveries.repository

import android.arch.lifecycle.LiveData
import com.sudansh.deliveries.data.ApiResponse
import com.sudansh.deliveries.data.AppExecutors
import com.sudansh.deliveries.data.NetworkBoundResource
import com.sudansh.deliveries.data.Resource
import com.sudansh.deliveries.repository.local.db.dao.DeliveryDao
import com.sudansh.deliveries.repository.local.db.entity.DeliveryItem
import com.sudansh.deliveries.repository.remote.api.DeliverApi
import com.sudansh.deliveries.testing.OpenForTesting

@OpenForTesting
class DeliveryRepository(
        val appExecutors: AppExecutors,
        val deliveryDao: DeliveryDao,
        val api: DeliverApi
) {

    fun getDeliveries(isFetch: Boolean = false): LiveData<Resource<List<DeliveryItem>>> {
        return object :
                NetworkBoundResource<List<DeliveryItem>, List<DeliveryItem>>(appExecutors) {
            override fun saveCallResult(item: List<DeliveryItem>) {
                deliveryDao.insert(item)
            }

            override fun shouldFetch(data: List<DeliveryItem>?): Boolean =
                    isFetch || data == null || data.isEmpty()

            override fun loadFromDb(): LiveData<List<DeliveryItem>> =
                    deliveryDao.getDeliveries()

            override fun createCall(): LiveData<ApiResponse<List<DeliveryItem>>> =
                    api.getDeliveries()
        }.asLiveData()
    }
}