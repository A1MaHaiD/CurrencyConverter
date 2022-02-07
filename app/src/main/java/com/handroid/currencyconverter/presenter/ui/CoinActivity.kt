package com.handroid.currencyconverter.presenter.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.handroid.currencyconverter.databinding.ActivityCoinBinding
import com.handroid.currencyconverter.presenter.viewmodel.CoinViewModel

class CoinActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this)[CoinViewModel::class.java]
    }

    private val binding by lazy {
        ActivityCoinBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.coinInfoList.observe(this){

        }
    }
}