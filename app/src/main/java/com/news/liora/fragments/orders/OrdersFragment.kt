package com.news.liora.fragments.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.news.liora.R
import com.news.liora.databinding.FragmentOrdersBinding


class OrdersFragment : Fragment() {
    private var _binding: FragmentOrdersBinding?=null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentOrdersBinding.inflate(inflater,container,false)
        return binding.root
    }

}