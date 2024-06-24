package com.example.tickets.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tickets.R
import com.example.tickets.databinding.FragmentMainBinding
import com.example.tickets.domain.models.OfferData
import com.example.tickets.domain.models.RecommendItem
import com.example.tickets.presentation.adapters.OfferAdapter
import com.example.tickets.presentation.adapters.RecommendationsAdapter
import com.example.tickets.presentation.models.main.MainScreenState
import com.example.tickets.presentation.models.main.MainScreenStateResponse
import com.example.tickets.presentation.viewmodels.main.MainViewModel
import com.example.util.BindingFragment
import com.example.util.debounce
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BindingFragment<FragmentMainBinding>() {

    private val viewModel by viewModel<MainViewModel>()
    private val offersAdapter = OfferAdapter()
    private val recommendAdapter =
        RecommendationsAdapter { recommendClick?.let { onClick -> onClick(it) } }
    private var recommendClick: ((RecommendItem) -> Unit)? = null
    private var mainBottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>? = null
    private var backCallBack: OnBackPressedCallback? = null
    private val navigateToSearch = debounce<String>(DELAY_NAV, lifecycleScope, true) { text ->
        if (text.isNotEmpty()) {
            hideKeyBoard()
            backCallBack?.remove()
            findNavController().navigate(
                R.id.action_mainFragment_to_searchFragment, bundleOf(
                    DEPARTURE to binding.departureSheet.text.toString(),
                    ARRIVAL to binding.arrivalSheet.text.toString()
                )
            )
            binding.arrivalSheet.text.clear()
        }
    }

    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        offers.adapter = offersAdapter
        recommendations.adapter = recommendAdapter
        arrival.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                departureSheet.text = departure.text
                mainBottomSheet.isVisible = true
                mainBottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
                arrivalSheet.requestFocus()
                val inputMethodManager =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.showSoftInput(binding.arrivalSheet, 1)
                backCallBack = requireActivity().onBackPressedDispatcher.addCallback {
                    mainBottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
                    mainBottomSheet.isVisible = false
                }
            }
        }
        setOnClickListeners()
        initBottomSheet()
        bind()
    }


    private fun bind() {
        setOnRecommendationClick()
        viewModel.getMainData()
        lifecycleScope.launch(Dispatchers.Main) {
            repeatOnLifecycle(lifecycle.currentState) {
                viewModel.mainScreenState.collect { result ->
                    render(result = result)
                }
            }
        }
        with(binding) {
            departure.doAfterTextChanged { text ->
                val townName = if (text.isNullOrEmpty()) {
                    EMPTY_NAME
                } else {
                    text
                }
                viewModel.debounceSave(townName = townName.toString())
            }
            arrivalSheet.doAfterTextChanged { text ->
                navigateToSearch(text.toString())
            }
        }
    }

    private fun render(result: MainScreenState) {
        recommendAdapter.submitList(result.listRecommend)
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
        setDepartureTown(townName)
        offersAdapter.submitList(data)
    }

    private fun showError(@StringRes message: Int, townName: String) = with(binding) {
        loading.isVisible = false
        offers.isVisible = false
        tvError.isVisible = true
        setDepartureTown(townName)
        tvError.text = getString(message)
    }

    private fun showLoading(townName: String) = with(binding) {
        loading.isVisible = true
        offers.isVisible = false
        tvError.isVisible = false
        setDepartureTown(townName)
    }

    private fun setDepartureTown(townName: String) = with(binding) {
        if (townName.isNotEmpty()) {
            departure.setText(townName)
            departureSheet.setText(townName)
        }
    }

    private fun setOnRecommendationClick() {
        recommendClick = { item ->
            hideKeyBoard()
            binding.arrivalSheet.setText(getString(item.destinationMane))
        }
    }

    private fun initBottomSheet() = with(binding) {
        mainBottomSheetBehavior =
            BottomSheetBehavior.from(binding.mainBottomSheet)
                .apply { BottomSheetBehavior.STATE_COLLAPSED }
        mainBottomSheet.isVisible = false
        mainBottomSheetBehavior?.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        mainBottomSheet.isVisible = true
                    }

                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        mainBottomSheet.isVisible = false
                        backCallBack?.remove()
                        hideKeyBoard()
                    }

                    else -> {
                        backCallBack?.remove()
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}

        })
    }

    private fun setOnClickListeners() = with(binding) {
        icClearSheet.setOnClickListener {
            arrivalSheet.text.clear()
        }
        difficultRouteLayout.setOnClickListener {
            backCallBack?.remove()
            findNavController().navigate(R.id.action_mainFragment_to_placeHolderFragment)
        }
        anywhereLayout.setOnClickListener {
            backCallBack?.remove()
            findNavController().navigate(R.id.action_mainFragment_to_placeHolderFragment)
        }
        hotTicketsLayout.setOnClickListener {
            backCallBack?.remove()
            findNavController().navigate(R.id.action_mainFragment_to_placeHolderFragment)
        }
        weekendsLayout.setOnClickListener {
            backCallBack?.remove()
            findNavController().navigate(R.id.action_mainFragment_to_placeHolderFragment)
        }
    }

    private fun hideKeyBoard() {
        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.arrivalSheet.windowToken, 0)
    }

    companion object
}

private const val EMPTY_NAME = ""
private const val DELAY_NAV = 1000L