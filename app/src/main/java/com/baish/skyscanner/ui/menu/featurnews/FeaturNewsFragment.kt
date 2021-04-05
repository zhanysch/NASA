package com.baish.skyscanner.ui.menu.featurnews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.baish.skyscanner.databinding.FeaturNewsBinding
import com.baish.skyscanner.databinding.FeaturesLayoutBinding
import com.baish.skyscanner.ui.menu.features.FeaturMarsAdapter
import com.baish.skyscanner.ui.menu.features.FeaturesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeaturNewsFragment : Fragment() {

   var binding : FeaturNewsBinding? = null

    private val vm by viewModel<FeaturesViewModel>()
    private val adapterFeaturApod by lazy { FeaturesApodAdapter(vm) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FeaturNewsBinding.inflate(layoutInflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.recyclerFeatures?.adapter = adapterFeaturApod
        setup()
        binding?.btnBack?.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setup() {
        vm.getLikesApod().observe(viewLifecycleOwner, Observer {
            adapterFeaturApod.submitList(it)
        })
    }
}