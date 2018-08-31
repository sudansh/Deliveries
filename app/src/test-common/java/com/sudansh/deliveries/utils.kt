package com.sudansh.deliveries

import com.sudansh.deliveries.repository.local.db.entity.DeliveryItem
import com.sudansh.deliveries.repository.local.db.entity.Location

fun createDelivery(id: Int, description: String, lat: Double, lng: Double, url: String): DeliveryItem {
    return DeliveryItem(id, description, Location(lat, lng, description + "lalamove"), url)
}

fun createListDelivery(description: String, lat: Double, lng: Double, url: String, count: Int = 10): List<DeliveryItem> {
    return (1 until count).map {
        DeliveryItem(it, description + it, Location(lat, lng, description + "lalamove" + it), url + it)
    }
}