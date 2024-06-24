package com.example.tickets.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tickets.R
import com.example.tickets.databinding.FragmentAllTicketsBinding
import com.example.tickets.domain.models.TicketItem
import com.example.tickets.presentation.adapters.TicketsAdapter
import com.example.tickets.presentation.models.tickets.AllTicketsScreenState
import com.example.tickets.presentation.models.tickets.AllTicketsScreenStateResponse
import com.example.tickets.presentation.viewmodels.tickets.AllTicketsViewModel
import com.example.util.BindingFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class AllTicketsFragment : BindingFragment<FragmentAllTicketsBinding>() {

    private val viewModel by viewModel<AllTicketsViewModel> {
        parametersOf(arguments?.getString(TICKET_INFO) ?: "")
    }
    private val ticketsAdapter = TicketsAdapter()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAllTicketsBinding {
        return FragmentAllTicketsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tickets.adapter = ticketsAdapter
        binding.icBack.setOnClickListener {
            findNavController().navigateUp()
        }
        bind()
    }

    private fun bind() {
        viewModel.getAllTicketsData()
        lifecycleScope.launch(Dispatchers.Main) {
            repeatOnLifecycle(lifecycle.currentState) {
                viewModel.allTicketsScreenState.collect { result ->
                    render(result = result)
                }
            }
        }
    }

    private fun render(result: AllTicketsScreenState) {
        with(binding) {
            route.text = "${result.ticketInfo.departureTown} — ${result.ticketInfo.arrivalTown}"
            flyInfo.text = "${result.ticketInfo.dateDeparture}, ${getString(R.string.one_person)}"
        }
        when (result.data) {

            is AllTicketsScreenStateResponse.Content -> {
                showContent(result.data.tickets)
            }

            is AllTicketsScreenStateResponse.Error -> {
                showError(result.data.message)
            }

            AllTicketsScreenStateResponse.IsLoading -> {
                showLoading()
            }

            is AllTicketsScreenStateResponse.NoInternet -> {
                showError(result.data.message)
            }
        }
    }

    private fun showContent(data: List<TicketItem>) = with(binding) {
        tickets.isVisible = true
        tvError.isVisible = false
        loading.isVisible = false
        ticketsAdapter.submitList(data)
    }

    private fun showError(@StringRes message: Int) = with(binding) {
        tickets.isVisible = false
        tvError.isVisible = true
        loading.isVisible = false
        tvError.text = getString(message)
    }

    private fun showLoading() = with(binding) {
        tickets.isVisible = false
        tvError.isVisible = false
        loading.isVisible = true
    }


    companion object

}

const val TICKET_INFO = "ticket info"