package com.baish.skyscanner.ui.main.mainside

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.baish.skyscanner.R
import com.baish.skyscanner.databinding.FragmentMainBinding
import com.baish.skyscanner.ui.main.video.VideoActivity
import com.baish.skyscanner.utils.ItemOffsetDecoration
import com.baish.skyscanner.utils.setCornerRadius

import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    var binding: FragmentMainBinding? = null
    private val vm by viewModel<MainViewModel>()
    private val adapterMain by lazy { MainRecycler(){
        findNavController().navigate(R.id.action_mainFragment_to_apodFragment)
    } }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupRecyclerMain()
        vm.loadItemsMain()
        binding?.recyclerMain?.adapter = adapterMain
        cutImages()
    }

    private fun setupRecyclerMain() {
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding?.recyclerMain)
        vm.getContentRoomMain().observe(viewLifecycleOwner, Observer {
            adapterMain.submitList(it)
        })
        binding?.recyclerMain?.addItemDecoration(
            ItemOffsetDecoration(
                itemLeftMargin = 10f,
                itemRightMargin = 10f,
                firstItemLeftMargin = 20f,
                lastItemRightMargin = 20f
            )
        )
    }
   

    private fun setupListeners() {
        binding?.constrTransfer?.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_videoFragment)
        }
        binding?.techport?.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_techProjectFragment)
        }

        binding?.constrMars?.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_marsFragment)
        }
        binding?.Images?.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_imageandVideoFragment)
        }
        menuClicks()
    }


    private fun menuClicks() {
        binding?.toolbar?.toolbar?.setOnMenuItemClickListener {
            when (it.itemId) {

                R.id.conact -> {
                    val client = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:zhanysch@gmai.com")
                    }
                    startActivity(Intent.createChooser(client, "contact"))
                    return@setOnMenuItemClickListener true
                }

                R.id.FAQ -> {
                    findNavController().navigate(R.id.action_mainFragment_to_faqFragment)
                    return@setOnMenuItemClickListener true
                }

                R.id.terms -> {
                    findNavController().navigate(R.id.action_mainFragment_to_termsConditionsFragment)
                    return@setOnMenuItemClickListener true
                }

                R.id.Privacy -> {
                    findNavController().navigate(R.id.action_mainFragment_to_privacyFragment)
                    return@setOnMenuItemClickListener true
                }

                R.id.features -> {
                    findNavController().navigate(R.id.action_mainFragment_to_featuresFragment)
                    return@setOnMenuItemClickListener true
                }
                else -> super.onOptionsItemSelected(it)
            }
        }
    }

    private fun cutImages() {
        val radius = resources.getDimension(R.dimen.mtrl_btn_corner_radius)
        binding?.imageTechport?.setCornerRadius(
            topRight = radius,
            topLeft = radius,
            bottomRight = radius,
            bottomLeft = radius
        )
        binding?.imageMars?.setCornerRadius(
            topRight = radius,
            topLeft = radius,
            bottomRight = radius,
            bottomLeft = radius
        )
        binding?.image?.setCornerRadius(
            topRight = radius,
            topLeft = radius,
            bottomRight = radius,
            bottomLeft = radius
        )
        binding?.imageTransfer?.setCornerRadius(
            topRight = radius,
            topLeft = radius,
            bottomRight = radius,
            bottomLeft = radius
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}


