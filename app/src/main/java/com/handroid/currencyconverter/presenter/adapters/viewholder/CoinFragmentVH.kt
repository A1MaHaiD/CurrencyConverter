package com.handroid.currencyconverter.presenter.adapters.viewholder

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.handroid.currencyconverter.databinding.FragmentItemBinding

class CoinFragmentVH(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
    val idView: TextView = binding.itemNumber
    val contentView: TextView = binding.content

    override fun toString(): String {
        return super.toString() + " '" + contentView.text + "'"
    }
}