package com.baish.skyscanner.ui.main.videoandimage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.baish.skyscanner.databinding.FragmentMainBinding
import com.baish.skyscanner.databinding.ImageVidellayoutBinding
import com.baish.skyscanner.ui.main.mainside.MainViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImageandVideoFragment : Fragment(){

    var binding: ImageVidellayoutBinding? = null
    private val adapterSearch by lazy { ImagevidAdapter() }
    private var searchJob : Job? = null
    private val vm by viewModel<ImageViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = ImageVidellayoutBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateSeacrhRepository()
        setupRecycler()

    }

    private fun setupRecycler() {
        vm.search.observe(viewLifecycleOwner, Observer {
            adapterSearch.submitList(it.items)
        })
    }


    private fun updateSeacrhRepository() {
        binding?.etSearch?.doAfterTextChanged {
            if (it?.isNotEmpty()!!)
                search(it.toString())
        }
    }
    private fun search(query: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            vm.startSearch(query)


        }
    }
}