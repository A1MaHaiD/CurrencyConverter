package com.handroid.currencyconverter.presenter.adapters

import androidx.recyclerview.widget.RecyclerView
import com.handroid.currencyconverter.databinding.ItemCoinInfoBinding

import javax.inject.Inject

class CoinInfoVH @Inject constructor(
    val binding: ItemCoinInfoBinding
) : RecyclerView.ViewHolder(binding.root)