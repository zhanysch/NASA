package com.baish.skyscanner.ui.main.apod

import android.os.Bundle
import android.view.View
import com.baish.skyscanner.R
import com.baish.skyscanner.data.common.BaseFragment
import com.baish.skyscanner.databinding.ApodLayoutBinding
import com.baish.skyscanner.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.view.*

class ApodFragment : BaseFragment(R.layout.apod_layout) {

    var binding: ApodLayoutBinding? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = ApodLayoutBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}