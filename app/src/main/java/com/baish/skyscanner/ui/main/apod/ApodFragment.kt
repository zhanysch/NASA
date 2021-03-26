package com.baish.skyscanner.ui.main.apod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.baish.skyscanner.databinding.ApodLayoutBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ApodFragment : Fragment() {

    var binding: ApodLayoutBinding? = null

    private val vm by viewModel<ApodViewModel>()
    private val adapterAPOD by lazy { ApodRecyclerAdapter(vm) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = ApodLayoutBinding.inflate(layoutInflater)

        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupApod()
        vm.loadImage()
        binding?.recyclerApod?.adapter = adapterAPOD


    }

    private fun setupApod() {
        val snapHelper : SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding?.recyclerApod)
        vm.getContentRoom().observe(viewLifecycleOwner, Observer {
            binding?.progresBar?.visibility = View.VISIBLE
            adapterAPOD.submitList(it)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}