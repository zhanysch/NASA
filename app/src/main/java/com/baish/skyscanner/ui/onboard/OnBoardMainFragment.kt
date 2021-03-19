package com.baish.skyscanner.ui.onboard

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.baish.skyscanner.R
import com.baish.skyscanner.data.local.PreferenceHelper
import com.baish.skyscanner.data.model.onboard.OnBoardModel
import com.baish.skyscanner.databinding.FragmentMainBinding
import com.baish.skyscanner.databinding.FragmentMainonboardBinding


class OnBoardMainFragment : Fragment() {

    private val list = arrayListOf<Fragment>()

    var binding: FragmentMainonboardBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainonboardBinding.inflate(layoutInflater)
        return binding?.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupViewPager()
    }


    private fun setupListeners() {
        binding?.onBoardViewPager?.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {}

            @SuppressLint("MissingSuperCall")
            override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                if (isLastPage(position)) {
                    binding?.tvNext?.text = "Finish"
                } else {
                    binding?.tvNext?.text = "Continue"
                }
            }

        })

        binding?.tvNext?.setOnClickListener {
            if (binding?.onBoardViewPager?.currentItem?.let { it1 -> isLastPage(it1) } == true) {
                PreferenceHelper.setIsFirstLaunch()
               findNavController().navigate(R.id.action_onBoardMainFragment_to_mainFragment)
            } else {
                binding?.onBoardViewPager?.currentItem =
                    binding?.onBoardViewPager?.currentItem?.plus(1)!!
            }
        }

        binding?.tvSkip?.setOnClickListener {
            PreferenceHelper.setIsFirstLaunch()
            findNavController().navigate(R.id.action_onBoardMainFragment_to_mainFragment)
        }
    }

    private fun isLastPage(position: Int) = position == list.size - 1

    private fun setupViewPager() {
        val adapter = OnBoardAdapter(childFragmentManager)
        binding?.onBoardViewPager?.adapter = adapter
        list.add(
                OnBoardFragment.getInstance(
                        OnBoardModel(
                                getString(R.string.failure),
                                R.drawable.space
                        )
                )
        )
        list.add(
                OnBoardFragment.getInstance(
                        OnBoardModel(
                                " dude ,If u need space \n join NASA",
                                R.drawable.blackhole
                        )))

        list.add(
                OnBoardFragment.getInstance(
                        OnBoardModel(
                                "For us , space is \n still a hight prority",
                                R.drawable.shuttle
                        )))

        adapter.update(list)
        binding?.onBoardTabLayout?.setupWithViewPager(binding?.onBoardViewPager)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}