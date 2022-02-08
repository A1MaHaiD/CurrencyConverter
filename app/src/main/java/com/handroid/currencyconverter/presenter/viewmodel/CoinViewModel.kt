package com.handroid.currencyconverter.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.handroid.currencyconverter.data.repository.CoinRepositoryImpl
import com.handroid.currencyconverter.domain.usecase.GetCoinItemUseCase
import com.handroid.currencyconverter.domain.usecase.GetCoinListUseCase
import com.handroid.currencyconverter.domain.usecase.LoadCoinDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val repositoryImpl: CoinRepositoryImpl
) : ViewModel() {

    private val getCoinListUseCase = GetCoinListUseCase(repositoryImpl)
    private val getCoinInfoUseCase = GetCoinItemUseCase(repositoryImpl)
    private val loadCoinDataUseCase = LoadCoinDataUseCase(repositoryImpl)

    val coinInfoList = getCoinListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        viewModelScope.launch {
            repositoryImpl.loadCoinDate()
        }
    }
}