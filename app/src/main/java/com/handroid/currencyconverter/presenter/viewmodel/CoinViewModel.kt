package com.handroid.currencyconverter.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.handroid.currencyconverter.domain.CoinRepository
import com.handroid.currencyconverter.domain.usecase.GetCoinItemUseCase
import com.handroid.currencyconverter.domain.usecase.GetCoinListUseCase
import com.handroid.currencyconverter.domain.usecase.LoadCoinDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val repository: CoinRepository,
    private val getCoinListUseCase: GetCoinListUseCase,
    private val getCoinInfoUseCase: GetCoinItemUseCase,
    private val loadCoinDataUseCase: LoadCoinDataUseCase
) : ViewModel() {

    val coinInfoList = getCoinListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        viewModelScope.launch {
            loadCoinDataUseCase.invoke()
        }
    }
}