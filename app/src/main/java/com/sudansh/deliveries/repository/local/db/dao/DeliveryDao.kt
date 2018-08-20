package com.sudansh.deliveries.repository.local.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.sudansh.deliveries.repository.local.db.entity.DeliveryItem

@Dao
interface DeliveryDao {

    @Query("SELECT * FROM deliveries")
    fun getDeliveries(): LiveData<List<DeliveryItem>>

    @Query("SELECT * FROM deliveries WHERE id= :id")
    fun findDeliveryById(id: Int): LiveData<List<DeliveryItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(deliveryItem: DeliveryItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<DeliveryItem>)
}