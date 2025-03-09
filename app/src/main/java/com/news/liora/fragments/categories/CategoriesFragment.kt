package com.news.liora.fragments.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.news.liora.R
import com.news.liora.databinding.FragmentCategoriesBinding
import com.news.liora.databinding.FragmentHomeBinding


class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding?=null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCategoriesBinding.inflate(inflater,container,false)
        return binding.root
    }

}