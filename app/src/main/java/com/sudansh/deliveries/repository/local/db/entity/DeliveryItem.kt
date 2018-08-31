package com.sudansh.deliveries.repository.local.db.entity

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "deliveries")
@Parcelize
data class DeliveryItem(
        @PrimaryKey(autoGenerate = true) val id: Int,
        val description: String,
        @Embedded val location: Location,
        val imageUrl: String
) : Parcelable

@Parcelize
data class Location(
        @SerializedName("lat") val lat: Double,
        @SerializedName("lng") val lng: Double,
        @SerializedName("address") val address: String
) : Parcelable