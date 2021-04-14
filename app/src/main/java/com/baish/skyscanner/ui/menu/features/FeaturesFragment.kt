package com.baish.skyscanner.ui.menu.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.baish.skyscanner.databinding.FeaturesLayoutBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeaturesFragment : Fragment(){
    var binding: FeaturesLayoutBinding? = null
    private val adapterFeaturMars by lazy { FeaturMarsAdapter(vm) }

    private val vm by viewModel<FeaturesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FeaturesLayoutBinding.inflate(layoutInflater)

        return binding?.root
    }

    @ExperimentalPagingApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val snapHelper : SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding?.recyclerFeatures)
       binding?.recyclerFeatures?.adapter = adapterFeaturMars
        setupVm()
        binding?.btnBack?.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    @ExperimentalPagingApi
    private fun setupVm() {
        vm.getPagingMarsLikes().observe(viewLifecycleOwner, Observer {
            vm.showFavourite(it)
        })

        vm.data.observe(viewLifecycleOwner, Observer {
            adapterFeaturMars.submitList(it)
        })
    }
}