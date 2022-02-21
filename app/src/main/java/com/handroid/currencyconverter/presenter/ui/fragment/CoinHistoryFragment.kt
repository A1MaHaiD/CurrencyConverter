package com.handroid.currencyconverter.presenter.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.handroid.currencyconverter.databinding.FragmentCoinHistoryBinding
import com.handroid.currencyconverter.CoinApp
import com.handroid.currencyconverter.presenter.adapters.HistoryInfoAdapter
import com.handroid.currencyconverter.presenter.viewmodel.HistoryViewModel
import com.handroid.currencyconverter.presenter.viewmodel.ViewModelFactory
import javax.inject.Inject

class CoinHistoryFragment : Fragment() {

    private val args by navArgs<CoinHistoryFragmentArgs>()

    private lateinit var viewModel: HistoryViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as CoinApp).component
    }

    private var _binding: FragmentCoinHistoryBinding? = null
    private val binding: FragmentCoinHistoryBinding
        get() = _binding ?: throw RuntimeException("CoinHistoryFragment == null")


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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = HistoryInfoAdapter(this.requireActivity())
        binding.rvHistoryList.adapter = adapter
        viewModel = ViewModelProvider(this, viewModelFactory)[HistoryViewModel::class.java]
        viewModel.getHistoryInfoMonth.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun getSymbol(): String {
        return args.fromSymbol
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "CoinHistoryFragment"
        const val TIME = 1642377600
    }
}