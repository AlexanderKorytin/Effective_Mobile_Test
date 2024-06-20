package com.example.tickets.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.example.tickets.databinding.FragmentMainBinding
import com.example.tickets.domain.models.OfferData
import com.example.tickets.presentation.adapters.OfferAdapter
import com.example.tickets.presentation.models.main.MainScreenState
import com.example.tickets.presentation.viewmodels.main.MainViewModel
import com.example.util.BindingFragment
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
        viewModel.mainScreenState.observe(viewLifecycleOwner) { result ->
            render(result = result)
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

    private fun render(result: MainScreenState) {
        when (result) {
            is MainScreenState.Content -> {
                showContent(data = result.offers, townName = result.town.name)
            }

            is MainScreenState.Error -> {
                showError(message = result.message, townName = result.town.name)
            }

            is MainScreenState.NoInternet -> {
                showNoInternet(message = result.message, townName = result.town.name)
            }

            is MainScreenState.IsLoading -> {
                showLoading(result.town.name)
            }
        }
    }

    private fun showContent(data: List<OfferData>, townName: String) = with(binding) {
        loading.isVisible = false
        offers.isVisible = true
        serverError.isVisible = false
        noInternet.isVisible = false
        if (townName.isNotEmpty()) {
            departure.setText(townName)
        }
        offersAdapter.submitList(data)
    }

    private fun showNoInternet(@StringRes message: Int, townName: String) = with(binding) {
        loading.isVisible = false
        offers.isVisible = false
        serverError.isVisible = false
        noInternet.isVisible = true
        if (townName.isNotEmpty()) {
            departure.setText(townName)
        }
        noInternet.text = getString(message)
    }

    private fun showError(@StringRes message: Int, townName: String) = with(binding) {
        loading.isVisible = false
        offers.isVisible = false
        serverError.isVisible = true
        noInternet.isVisible = false
        if (townName.isNotEmpty()) {
            departure.setText(townName)
        }
        serverError.text = getString(message)
    }

    private fun showLoading(townName: String) = with(binding) {
        loading.isVisible = true
        offers.isVisible = false
        serverError.isVisible = false
        noInternet.isVisible = false
        if (townName.isNotEmpty()) {
            departure.setText(townName)
        }
    }

    companion object
}

private const val EMPTY_NAME = ""