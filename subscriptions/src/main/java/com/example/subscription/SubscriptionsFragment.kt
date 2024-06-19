package com.example.subscription

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.subscription.databinding.FragmentSubscriptionsBinding
import com.example.util.BindingFragment


class SubscriptionsFragment : BindingFragment<FragmentSubscriptionsBinding>() {

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSubscriptionsBinding {
        return FragmentSubscriptionsBinding.inflate(inflater, container, false)
    }

    companion object {

    }
}