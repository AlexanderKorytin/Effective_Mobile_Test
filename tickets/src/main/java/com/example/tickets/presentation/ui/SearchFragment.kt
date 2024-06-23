package com.example.tickets.presentation.ui

import android.app.DatePickerDialog
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
import com.example.tickets.databinding.FragmentSearchBinding
import com.example.tickets.domain.models.RecommendTicket
import com.example.tickets.presentation.adapters.RecommendTicketsAdapter
import com.example.tickets.presentation.models.search.SearchScreenState
import com.example.tickets.presentation.models.search.SearchScreenStateResponse
import com.example.tickets.presentation.viewmodels.search.SearchViewModel
import com.example.util.BindingFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.util.Calendar


class SearchFragment : BindingFragment<FragmentSearchBinding>() {

    private val viewModel by viewModel<SearchViewModel> {
        parametersOf(arguments?.getString(DEPARTURE), arguments?.getString(ARRIVAL) ?: "")
    }
    private val adapter = RecommendTicketsAdapter()
    private val thereDate = Calendar.getInstance()
    private val backDate = Calendar.getInstance()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
        bind()
    }

    private fun bind() {
        viewModel.getSearchData()
        binding.tickets.adapter = adapter
        lifecycleScope.launch(Dispatchers.Main) {
            repeatOnLifecycle(lifecycle.currentState) {
                viewModel.searchScreenState.collect { result ->
                    render(result = result)
                }
            }
        }
    }

    private fun render(result: SearchScreenState) {
        with(binding) {
            arrival.setText(result.arrivalTown)
            departure.setText(result.departureTown)
            thereDayMon.text = result.thereDate
            thereDayName.text = result.thereDayName
            if (result.backDate.isNotEmpty()) {
                backDataIcon.isVisible = false
                backDataValue.text = result.backDate
            } else {
                backDataIcon.isVisible = true
                backDataValue.text = getString(R.string.back)
            }
        }
        when (result.data) {
            is SearchScreenStateResponse.Content -> {
                showContent(data = result.data.tickets)
            }

            is SearchScreenStateResponse.Error -> {
                showError(message = result.data.message)
            }

            is SearchScreenStateResponse.NoInternet -> {
                showError(message = result.data.message)
            }

            is SearchScreenStateResponse.IsLoading -> {
                showLoading()
            }
        }
    }

    private fun showContent(data: List<RecommendTicket>) = with(binding) {
        loading.isVisible = false
        containerTickets.isVisible = true
        tvError.isVisible = false
        adapter.submitList(data)
    }

    private fun showError(@StringRes message: Int) = with(binding) {
        loading.isVisible = false
        containerTickets.isVisible = false
        tvError.isVisible = true
        tvError.text = getString(message)
    }

    private fun showLoading() = with(binding) {
        loading.isVisible = true
        containerTickets.isVisible = false
        tvError.isVisible = false
    }

    private fun setOnClickListeners() = with(binding) {
        containerThereData.setOnClickListener {
            changedThereDate(thereDate)
        }
        containerBackData.setOnClickListener {
            changedBackDate(backDate)
        }
        icChange.setOnClickListener {
            viewModel.destinationChanged()
        }
        icBack.setOnClickListener {
            findNavController().navigateUp()
        }
        icClear.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun changedThereDate(date: Calendar) {
        val dateDialogClick =
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                date.set(Calendar.YEAR, year)
                date.set(Calendar.MONTH, month)
                date.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                viewModel.setThereDate(date)
            }
        DatePickerDialog(
            requireContext(),
            dateDialogClick,
            date.get(Calendar.YEAR),
            date.get(Calendar.MONTH),
            date.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun changedBackDate(date: Calendar) {
        val dateDialogClick =
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                date.set(Calendar.YEAR, year)
                date.set(Calendar.MONTH, month)
                date.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                viewModel.setBackDate(date)
            }
        DatePickerDialog(
            requireContext(),
            dateDialogClick,
            date.get(Calendar.YEAR),
            date.get(Calendar.MONTH),
            date.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
}

const val DEPARTURE = "departure"
const val ARRIVAL = " arrival"