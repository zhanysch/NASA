package com.baish.skyscanner.ui.main.apod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.baish.skyscanner.R
import com.baish.skyscanner.data.common.BaseFragment
import com.baish.skyscanner.databinding.ApodLayoutBinding
import com.baish.skyscanner.databinding.FragmentMainBinding
import com.baish.skyscanner.ui.main.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.apod_layout.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Observer

class ApodFragment : Fragment() {

    var binding: ApodLayoutBinding? = null

    private val vm by viewModel<ApodViewModel>()
    private val adapterAPOD by lazy { ApodRecyclerAdapter() }

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
        vm.image.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            adapterAPOD.submitList(it)
            binding?.progresBar?.visibility = View.VISIBLE
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}