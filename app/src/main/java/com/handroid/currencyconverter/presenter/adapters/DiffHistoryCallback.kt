package com.handroid.currencyconverter.presenter.adapters

import androidx.recyclerview.widget.DiffUtil
import com.handroid.currencyconverter.domain.entity.HistoryInfoEntity

object DiffHistoryCallback : DiffUtil.ItemCallback<HistoryInfoEntity>() {
    override fun areItemsTheSame(
        oldItem: HistoryInfoEntity,
        newItem: HistoryInfoEntity
    ): Boolean {
        return oldItem.time == oldItem.time
    }

    override fun areContentsTheSame(
        oldItem: HistoryInfoEntity,
        newItem: HistoryInfoEntity
    ): Boolean {
        return oldItem == newItem
    }
}