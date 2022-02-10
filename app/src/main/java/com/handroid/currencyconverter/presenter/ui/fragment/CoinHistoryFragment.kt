package com.handroid.currencyconverter.presenter.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.handroid.currencyconverter.databinding.FragmentCoinHistoryBinding
import com.handroid.currencyconverter.presenter.viewmodel.HistoryViewModel
import com.handroid.currencyconverter.presenter.viewmodel.ViewModelFactory
import javax.inject.Inject

class CoinHistoryFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[HistoryViewModel::class.java]
    }

    private var _binding: FragmentCoinHistoryBinding? = null
    private val binding: FragmentCoinHistoryBinding
        get() = _binding ?: throw RuntimeException("CoinHistoryFragment == null")

    override

    fun onCreate(savedInstanceState: Bundle?) {
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
    }

    companion object {
        fun newInstance(arg: String) =
            CoinHistoryFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}