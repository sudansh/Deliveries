package com.sudansh.deliveries.ui

import android.support.v7.util.DiffUtil
import com.sudansh.deliveries.repository.local.db.entity.DeliveryItem

class DeliverDiffUtil(private val newList: List<DeliveryItem>, private val oldList: List<DeliveryItem>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            newList[newItemPosition].id == oldList[oldItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            newList[newItemPosition] == oldList[oldItemPosition]

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size
}