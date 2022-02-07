package com.handroid.currencyconverter.presenter.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.handroid.currencyconverter.databinding.ActivityCoinDetailBinding
import com.handroid.currencyconverter.presenter.viewmodel.CoinListViewModel


class CoinDetailActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this)[CoinListViewModel::class.java]
    }

    private val binding by lazy {
        ActivityCoinDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL) ?: ""
        viewModel.getDetailInfo(fromSymbol).observe(this)
        {

        }
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"
        private const val EMPTY_SYMBOL = ""

        fun newIntent(context: Context, fromSymbol: String) =
            Intent(context, CoinDetailActivity::class.java).apply {
                putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            }
    }
}