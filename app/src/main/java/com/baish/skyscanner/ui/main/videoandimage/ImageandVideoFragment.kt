package com.baish.skyscanner.ui.main.videoandimage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.baish.skyscanner.data.model.nasa.nasaimage.Items
import com.baish.skyscanner.databinding.ImageVidellayoutBinding
import com.baish.skyscanner.utils.toTransitionGroup
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImageandVideoFragment : Fragment(){

    var binding: ImageVidellayoutBinding? = null
    private val adapterSearch by lazy { ImagevidAdapter(){
        item: Items, image: ShapeableImageView ->  
        navigateToDetails(item,image)
    } }

    private fun navigateToDetails(item: Items, image: ShapeableImageView) {
        val extras = FragmentNavigatorExtras(image.toTransitionGroup())
        val destinatin = ImageandVideoFragmentDirections.actionImageandVideoFragmentToImageDetails2(item)
        findNavController().navigate(destinatin,extras)
    }

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
        binding?.btnBack?.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupRecycler() {
        binding?.recycler?.adapter = adapterSearch
        vm.search.observe(viewLifecycleOwner, Observer {
            adapterSearch.submitList(it.items)
            if(it.items.isNullOrEmpty() && !binding?.etSearch?.text.isNullOrEmpty()){
                binding?.recycler?.visibility = View.GONE
                binding?.imagemain?.visibility = View.VISIBLE
                binding?.textNotFound?.visibility = View.VISIBLE
                binding?.textMain?.visibility = View.GONE
            }else if (
                it.items.isNullOrEmpty()&& binding?.etSearch?.text.isNullOrEmpty()
            ){
                binding?.bydefault?.isVisible = true
            }
            else{
                binding?.recycler?.visibility = View.VISIBLE
                binding?.imagemain?.visibility = View.GONE
                binding?.textNotFound?.visibility = View.GONE
            }
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
            binding?.imagemain?.visibility = View.VISIBLE
            binding?.textNotFound?.visibility = View.VISIBLE
        }
    }
}