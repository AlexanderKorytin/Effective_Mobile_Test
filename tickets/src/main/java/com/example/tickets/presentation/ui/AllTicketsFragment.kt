package com.example.tickets.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tickets.R
import com.example.tickets.databinding.FragmentAllTicketsBinding
import com.example.util.BindingFragment


class AllTicketsFragment : BindingFragment<FragmentAllTicketsBinding>() {

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAllTicketsBinding {
        return FragmentAllTicketsBinding.inflate(inflater, container, false)
    }

    companion object {

    }

}