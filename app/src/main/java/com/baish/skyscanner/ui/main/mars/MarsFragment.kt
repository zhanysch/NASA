package com.baish.skyscanner.ui.main.mars

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
import com.baish.skyscanner.databinding.MarsLayoutBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MarsFragment : Fragment() {

    var binding: MarsLayoutBinding? = null
    private val vm by viewModel<MarsViewModel>()
    private val adapterMars by lazy { MarsRecyclerAdapter(vm) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = MarsLayoutBinding.inflate(layoutInflater)
        return binding?.root
    }

    @ExperimentalPagingApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val snapHelper : SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding?.recyclerMars)
        binding?.recyclerMars?.adapter = adapterMars
        setupViewModel()

        binding?.btnBack?.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    @ExperimentalPagingApi
    private fun setupViewModel() {
        vm.getPagindMarsData().observe(
            viewLifecycleOwner, Observer {
                adapterMars.submitData(lifecycle,it)
            }
        )
    }
}