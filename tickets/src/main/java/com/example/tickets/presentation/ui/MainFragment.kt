package com.example.tickets.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.tickets.databinding.FragmentMainBinding
import com.example.util.BindingFragment

class MainFragment : BindingFragment<FragmentMainBinding>() {

    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    companion object
}