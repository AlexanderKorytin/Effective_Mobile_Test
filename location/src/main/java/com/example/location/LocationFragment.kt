package com.example.location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
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

    companion object {

    }
}