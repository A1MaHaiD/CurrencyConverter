package com.handroid.currencyconverter.presenter.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.handroid.currencyconverter.databinding.ActivityCoinBinding

class CoinActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCoinBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}