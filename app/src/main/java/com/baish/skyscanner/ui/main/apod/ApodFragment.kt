package com.baish.skyscanner.ui.main.apod

import android.os.Bundle
import android.view.View
import com.baish.skyscanner.R
import com.baish.skyscanner.data.common.BaseFragment
import com.baish.skyscanner.databinding.ApodLayoutBinding
import com.baish.skyscanner.databinding.FragmentMainBinding
import com.baish.skyscanner.ui.main.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_main.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Observer

class ApodFragment : BaseFragment(R.layout.apod_layout) {

    var binding: ApodLayoutBinding? = null

    private val vm by viewModel<ApodViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = ApodLayoutBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupApod()
        vm.loadImage()
    }

    private fun setupApod() {
        vm.image.observe(viewLifecycleOwner, androidx.lifecycle.Observer {

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}