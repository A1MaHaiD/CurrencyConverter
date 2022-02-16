package com.handroid.currencyconverter.presenter.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.handroid.currencyconverter.R
import com.handroid.currencyconverter.presenter.CoinApp
import com.handroid.currencyconverter.databinding.FragmentCoinDetailBinding
import com.handroid.currencyconverter.presenter.viewmodel.CoinViewModel
import com.handroid.currencyconverter.presenter.viewmodel.ViewModelFactory
import com.squareup.picasso.Picasso
import javax.inject.Inject

class CoinDetailFragment : Fragment() {

    private val args by navArgs<CoinDetailFragmentArgs>()

    private lateinit var viewModel: CoinViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as CoinApp).component
    }

    private var _binding: FragmentCoinDetailBinding? = null
    private val binding: FragmentCoinDetailBinding
        get() = _binding ?: throw RuntimeException("CoinDetailFragment == null")


    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]
        viewModel.getDetailInfo(getSymbol()).observe(viewLifecycleOwner) {
            with(binding) {
                tvPrice.text = it.price.toString()
                tvMinPrice.text = it.lowDay.toString()
                tvMaxPrice.text = it.highDay.toString()
                tvLastMarket.text = it.lastMarket.toString()
                tvLastUpdate.text = it.lastUpdate
                tvFromSymbol.text = it.fromSymbol
                tvToSymbol.text = it.toSymbol
                Picasso.get().load(it.imageUrl).into(ivLogoCoin)
            }
        }
        binding.ivLogoCoin.setOnClickListener {
            launchCoinHistoryFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getSymbol(): String {
        return args.fromSymbol
    }

    private fun launchCoinHistoryFragment() {
        findNavController().navigate(
            CoinDetailFragmentDirections.actionCoinDetailFragmentToCoinHistoryFragment(getSymbol()))
    }
}