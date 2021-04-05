package com.baish.skyscanner.ui.main.apod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.baish.skyscanner.R
import com.baish.skyscanner.databinding.ApodLayoutBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ApodFragment : Fragment() {

    var binding: ApodLayoutBinding? = null


    private val vm by viewModel<ApodViewModel>()
    private val adapterAPOD by lazy { ApodRecyclerAdapter(vm)}

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


      /*  binding?.recyclerApod?.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx:Int, dy:Int) {
                super.onScrolled(recyclerView, dx, dy)
               val  currentPosition = llm.findFirstVisibleItemPosition()
                downloadFab.setOnClickListener(object:View.OnClickListener() {
                    fun onClick(view:View) {}
                })
            }
        })*/

    }

    private fun setupApod() {
        val snapHelper : SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding?.recyclerApod)
        vm.getContentRoom().observe(viewLifecycleOwner, Observer {
            binding?.progresBar?.visibility = View.VISIBLE
            adapterAPOD.submitList(it)
        })
        binding?.btnBack?.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}