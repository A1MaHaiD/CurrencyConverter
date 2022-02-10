package com.handroid.currencyconverter.presenter.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.handroid.currencyconverter.CoinApp
import com.handroid.currencyconverter.databinding.ActivityCoinBinding
import com.handroid.currencyconverter.presenter.viewmodel.CoinViewModel
import com.handroid.currencyconverter.presenter.viewmodel.ViewModelFactory
import javax.inject.Inject

class CoinActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]
    }

    private val binding by lazy {
        ActivityCoinBinding.inflate(layoutInflater)
    }

/*    private val component by lazy {
        (application as CoinApp).component
            .activityComponentFactory()
            .create()
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.coinInfoList.observe(this) {

        }
    }
}