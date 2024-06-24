package com.example.hotels.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.hotels.databinding.FragmentHotelsBinding
import com.example.util.BindingFragment


class HotelsFragment : BindingFragment<FragmentHotelsBinding>() {

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHotelsBinding {
        return FragmentHotelsBinding.inflate(inflater, container, false)
    }

    companion object
}