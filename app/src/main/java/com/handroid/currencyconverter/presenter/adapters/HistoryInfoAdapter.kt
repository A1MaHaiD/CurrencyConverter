package com.handroid.currencyconverter.presenter.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.handroid.currencyconverter.R
import com.handroid.currencyconverter.databinding.ItemDayHistoryBinding
import com.handroid.currencyconverter.domain.entity.HistoryInfoEntity
import javax.inject.Inject

class HistoryInfoAdapter @Inject constructor(
    private val context: Context
) :
    ListAdapter<HistoryInfoEntity, HistoryInfoVH>(DiffHistoryCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryInfoVH {
        val binding =
            ItemDayHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryInfoVH(binding)
    }

    override fun onBindViewHolder(holder: HistoryInfoVH, position: Int) {
        val day = getItem(position)
        with(holder.binding) {
            with(day) {
                val lastUpdateTemplate =
                    context.resources.getString(R.string.date)
                tvDate.text = String.format(lastUpdateTemplate, time)
                tvMinPrice.text = low
                tvMaxPrice.text = high
                tvOpen.text = open
                tvClose.text = close
            }
        }
    }
}