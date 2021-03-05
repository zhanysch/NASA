package com.baish.skyscanner.ui.menu

import android.os.Bundle
import com.baish.skyscanner.R
import com.baish.skyscanner.data.common.BaseFragment
import com.baish.skyscanner.databinding.FaqLayoutBinding
import com.baish.skyscanner.databinding.FragmentMainBinding

class FaqFragment : BaseFragment(R.layout.faq_layout) {

    var binding : FaqLayoutBinding? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = FaqLayoutBinding.inflate(layoutInflater)
    }
}