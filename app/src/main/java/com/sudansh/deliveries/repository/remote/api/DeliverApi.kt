package com.sudansh.deliveries.repository.remote.api

import android.arch.lifecycle.LiveData
import com.sudansh.deliveries.data.ApiResponse
import com.sudansh.deliveries.repository.local.db.entity.DeliveryItem
import retrofit2.http.GET
import retrofit2.http.Url


interface DeliverApi {

    @GET()
    fun getDeliveries(@Url url: String = "https://api.myjson.com/bins/gut34"): LiveData<ApiResponse<List<DeliveryItem>>>

//    @GET("deliveries")
//    fun getDeliveries(@Query("offset") offset: Int? = null): LiveData<ApiResponse<List<DeliveryItem>>>
}