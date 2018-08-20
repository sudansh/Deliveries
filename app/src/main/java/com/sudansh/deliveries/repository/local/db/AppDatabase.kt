package com.sudansh.deliveries.repository.local.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.sudansh.deliveries.repository.local.db.dao.DeliveryDao
import com.sudansh.deliveries.repository.local.db.entity.DeliveryItem


@Database(entities = [DeliveryItem::class],
        exportSchema = false,
        version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun deliveryDao(): DeliveryDao
}