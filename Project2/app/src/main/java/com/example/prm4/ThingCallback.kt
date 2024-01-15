package com.example.prm4

import androidx.recyclerview.widget.DiffUtil
import com.example.prm4.data.model.ThingsEntity

class ThingCallback(val notSorted: List<ThingsEntity>, val sorted: List<ThingsEntity>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = notSorted.size

    override fun getNewListSize(): Int = sorted.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        notSorted[oldItemPosition] === sorted[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        notSorted[oldItemPosition] == sorted[newItemPosition]
}