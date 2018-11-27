package com.sudansh.deliveries.di

import android.arch.persistence.room.Room
import com.sudansh.deliveries.data.AppExecutors
import com.sudansh.deliveries.repository.DeliveryRepository
import com.sudansh.deliveries.repository.local.db.AppDatabase
import org.koin.dsl.module.module


val localModule = module(definition = {
    single(definition = { AppExecutors() })
    single(definition = { DeliveryRepository(get(), get(), get()) })
    single(definition = {
        Room.databaseBuilder(get(), AppDatabase::class.java, "delivery-db").build()
    })
    single(definition = { get<AppDatabase>().deliveryDao() })
})