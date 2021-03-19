package com.baish.skyscanner.ui.menu.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.ExperimentalPagingApi
import com.baish.skyscanner.databinding.FeaturesLayoutBinding
import com.baish.skyscanner.databinding.FragmentMainBinding
import com.baish.skyscanner.ui.main.mars.MarsViewModel
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
       binding?.recyclerFeatures?.adapter = adapterFeaturMars

        setupVm()
    }

    @ExperimentalPagingApi
    private fun setupVm() {
        vm.getPagingMarsLikes().observe(viewLifecycleOwner, Observer {
            adapterFeaturMars.submitList(it)
        })
    }
}