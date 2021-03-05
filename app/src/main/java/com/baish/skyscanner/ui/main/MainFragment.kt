package com.baish.skyscanner.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.baish.skyscanner.R
import com.baish.skyscanner.data.common.BaseFragment
import com.baish.skyscanner.databinding.FragmentMainBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment(R.layout.fragment_main) {

    var binding : FragmentMainBinding? = null
    private val vm by viewModel<MainViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = FragmentMainBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTechProjects()
        setupListeners()
        vm.loadProjects()
       setHasOptionsMenu(true)
        binding?.toolbar?.toolbar?.inflateMenu(R.menu.menu_main)
    }

    private fun setupListeners() {
        apod.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_apodFragment)
        }
    }

    private fun setupTechProjects() {
        vm.project.observe(viewLifecycleOwner, Observer {
            binding?.textofImage?.text = it.title
            Picasso.get().load(it.status).into(binding?.imageTwo)
        })
    }



    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}


