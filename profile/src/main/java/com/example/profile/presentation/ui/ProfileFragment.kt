package com.example.profile.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.profile.databinding.FragmentProfileBinding
import com.example.util.BindingFragment


class ProfileFragment : BindingFragment<FragmentProfileBinding>() {

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater, container, false)
    }

    companion object
}