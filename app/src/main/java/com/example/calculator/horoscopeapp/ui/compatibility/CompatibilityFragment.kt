package com.example.calculator.horoscopeapp.ui.compatibility

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.calculator.R
import com.example.calculator.horoscopeapp.ui.list.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompatibilityFragment : Fragment() {

    private val viewModel by viewModels<ListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compatibility, container, false)
    }

}