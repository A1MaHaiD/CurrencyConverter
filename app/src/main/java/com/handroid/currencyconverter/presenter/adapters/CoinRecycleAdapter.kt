package com.handroid.currencyconverter.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.handroid.currencyconverter.databinding.FragmentItemBinding
import com.handroid.currencyconverter.presenter.adapters.viewholder.CoinFragmentVH
import com.handroid.currencyconverter.presenter.ui.fragment.placeholder.PlaceholderContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class CoinRecycleAdapter(
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<CoinFragmentVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinFragmentVH {

        return CoinFragmentVH(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: CoinFragmentVH, position: Int) {
        val item = values[position]
        holder.idView.text = item.id
        holder.contentView.text = item.content
    }

    override fun getItemCount(): Int = values.size



}