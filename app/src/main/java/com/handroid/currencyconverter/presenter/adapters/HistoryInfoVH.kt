package com.handroid.currencyconverter.presenter.adapters

import androidx.recyclerview.widget.RecyclerView
import com.handroid.currencyconverter.databinding.ItemDayHistoryBinding
import javax.inject.Inject

class HistoryInfoVH @Inject constructor(
    val binding: ItemDayHistoryBinding
) : RecyclerView.ViewHolder(binding.root)