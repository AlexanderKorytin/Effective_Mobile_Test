package com.example.tickets.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.tickets.databinding.FragmentPlaceHolderBinding
import com.example.util.BindingFragment


class PlaceHolderFragment : BindingFragment<FragmentPlaceHolderBinding>() {

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPlaceHolderBinding {
        return FragmentPlaceHolderBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.placeholderBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    companion object
}