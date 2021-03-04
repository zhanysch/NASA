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
import kotlinx.android.synthetic.main.fragment_mainonboard.*

class OnBoardMainFragment : Fragment() {

    private val list = arrayListOf<Fragment>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mainonboard,container,false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupViewPager()
    }


    private fun setupListeners() {
        onBoardViewPager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {}

            @SuppressLint("MissingSuperCall")
            override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                if (isLastPage(position)) {
                    tvNext.text = "Finish"
                } else {
                    tvNext.text = "Continue"
                }
            }

        })

        tvNext.setOnClickListener {
            if (isLastPage(onBoardViewPager.currentItem)) {
                PreferenceHelper.setIsFirstLaunch()
               findNavController().navigate(R.id.action_onBoardMainFragment_to_mainFragment)
            } else {
                onBoardViewPager.currentItem += 1
            }
        }

        tvSkip.setOnClickListener {
            PreferenceHelper.setIsFirstLaunch()
            findNavController().navigate(R.id.action_onBoardMainFragment_to_mainFragment)
        }
    }

    private fun isLastPage(position: Int) = position == list.size - 1

    private fun setupViewPager() {
        val adapter = OnBoardAdapter(childFragmentManager)
        onBoardViewPager.adapter = adapter
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
        onBoardTabLayout.setupWithViewPager(onBoardViewPager)
    }
}