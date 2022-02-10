package com.handroid.currencyconverter.presenter.adapters

import androidx.recyclerview.widget.DiffUtil
import com.handroid.currencyconverter.domain.entity.CoinInfoEntity

class DiffCoinInfoCallback : DiffUtil.ItemCallback<CoinInfoEntity>() {
    override fun areItemsTheSame(oldItem: CoinInfoEntity, newItem: CoinInfoEntity): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CoinInfoEntity, newItem: CoinInfoEntity): Boolean {
        return oldItem == newItem
    }
}