package com.example.tickets.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.example.tickets.databinding.FragmentMainBinding
import com.example.tickets.domain.models.OfferData
import com.example.tickets.presentation.adapters.OfferAdapter
import com.example.tickets.presentation.models.main.MainScreeState
import com.example.tickets.presentation.models.main.MainScreenStateResponse
import com.example.tickets.presentation.viewmodels.main.MainViewModel
import com.example.util.BindingFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BindingFragment<FragmentMainBinding>() {

    private val viewModel by viewModel<MainViewModel>()
    private val offersAdapter = OfferAdapter()

    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.offers.adapter = offersAdapter
        bind()
    }

    private fun bind() {
        viewModel.getMainData()
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.mainScreenState.collect { result ->
                render(result = result)

            }
        }
        binding.departure.doAfterTextChanged { text ->
            val townName = if (text.isNullOrEmpty()) {
                EMPTY_NAME
            } else {
                text
            }
            viewModel.debounceSave(townName = townName.toString())
        }
    }

    private fun render(result: MainScreeState) {
        when (result.data) {
            is MainScreenStateResponse.Content -> {
                showContent(data = result.data.offers, townName = result.departureTown.name)
            }

            is MainScreenStateResponse.Error -> {
                showError(message = result.data.message, townName = result.departureTown.name)
            }

            is MainScreenStateResponse.NoInternet -> {
                showError(message = result.data.message, townName = result.departureTown.name)
            }

            is MainScreenStateResponse.IsLoading -> {
                showLoading(result.departureTown.name)
            }
        }
    }

    private fun showContent(data: List<OfferData>, townName: String) = with(binding) {
        loading.isVisible = false
        offers.isVisible = true
        tvError.isVisible = false

        if (townName.isNotEmpty()) {
            departure.setText(townName)
        }
        offersAdapter.submitList(data)
    }

    private fun showError(@StringRes message: Int, townName: String) = with(binding) {
        loading.isVisible = false
        offers.isVisible = false
        tvError.isVisible = true
        if (townName.isNotEmpty()) {
            departure.setText(townName)
        }
        tvError.text = getString(message)
    }

    private fun showLoading(townName: String) = with(binding) {
        loading.isVisible = true
        offers.isVisible = false
        tvError.isVisible = false
        if (townName.isNotEmpty()) {
            departure.setText(townName)
        }
    }

    companion object
}

private const val EMPTY_NAME = ""