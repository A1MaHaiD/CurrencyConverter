package com.handroid.currencyconverter.presenter.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.handroid.currencyconverter.databinding.ActivityCoinPriceListBinding
import com.handroid.currencyconverter.presenter.viewmodel.CoinListViewModel

class CoinPriceListActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this)[CoinListViewModel::class.java]
    }

    private val binding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.coinInfoList.observe(this){

        }
    }
}