package com.handroid.currencyconverter.presenter.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.handroid.currencyconverter.databinding.FragmentCoinListBinding
import com.handroid.currencyconverter.domain.entity.CoinInfoEntity
import com.handroid.currencyconverter.presenter.CoinApp
import com.handroid.currencyconverter.presenter.adapters.CoinInfoAdapter
import com.handroid.currencyconverter.presenter.viewmodel.CoinViewModel
import com.handroid.currencyconverter.presenter.viewmodel.ViewModelFactory
import javax.inject.Inject

class CoinListFragment : Fragment() {

    private lateinit var viewModel: CoinViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentCoinListBinding? = null
    private val binding: FragmentCoinListBinding
        get() = _binding ?: throw RuntimeException("$TAG == null")

    private val component by lazy {
        (requireActivity().application as CoinApp).component
    }


    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CoinInfoAdapter(this.requireActivity())
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinInfoDTO: CoinInfoEntity) {
                launchCoinDetailFragment(coinInfoDTO.fromSymbol)
            }
        }
        binding.rvCoinPriceList.adapter = adapter
        binding.rvCoinPriceList.itemAnimator = null
        Log.d(TAG, "onViewCreated - rvCoinPriceList")
        viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchCoinDetailFragment(
        fromSymbol: String
    ) {
        Log.d(TAG, "launchCoinDetailFragment")
        findNavController().navigate(
            CoinListFragmentDirections.actionCoinListFragmentToCoinDetailFragment(
                fromSymbol
            )
        )
    }

    companion object {
        const val TAG = "CoinListFragment"
    }
}