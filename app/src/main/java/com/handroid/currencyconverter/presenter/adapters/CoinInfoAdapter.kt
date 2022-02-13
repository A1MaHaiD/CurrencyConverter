package com.handroid.currencyconverter.presenter.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.handroid.currencyconverter.R
import com.handroid.currencyconverter.databinding.ItemCoinInfoBinding
import com.handroid.currencyconverter.domain.entity.CoinInfoEntity
import com.handroid.currencyconverter.presenter.CoinApp
import com.handroid.currencyconverter.presenter.ui.fragment.CoinListFragment
import com.squareup.picasso.Picasso
import javax.inject.Inject

class CoinInfoAdapter @Inject constructor(
    private val context: Context,
) :
    ListAdapter<CoinInfoEntity, CoinInfoVH>(DiffCoinInfoCallback) {
    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoVH {
        Log.d("CoinInfoAdapter", "onCreateViewHolder")
        val binding =
            ItemCoinInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CoinInfoVH(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoVH, position: Int) {
        val coin = getItem(position)
        with(holder.binding) {
            with(coin) {
                val symbolsTemplate = context.resources.getString(R.string.symbols_template)
                val lastUpdateTemplate =
                    context.resources.getString(R.string.last_update_template)
                tvSymbols.text = String.format(symbolsTemplate, fromSymbol, toSymbol)
                tvPrice.text = price.toString()
                tvLastUpdate.text =
                    String.format(lastUpdateTemplate, lastUpdate)
                Picasso.get().load(imageUrl).into(ivLogoCoin)
                root.setOnClickListener {
                    onCoinClickListener?.onCoinClick(this)
                }
            }
        }
    }

    interface OnCoinClickListener {
        fun onCoinClick(coinInfoDTO: CoinInfoEntity)
    }
}