package com.example.location.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.location.databinding.FragmentLocationBinding
import com.example.util.BindingFragment


class LocationFragment : BindingFragment<FragmentLocationBinding>() {

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLocationBinding {
        return FragmentLocationBinding.inflate(inflater, container, false)
    }

    companion object
}