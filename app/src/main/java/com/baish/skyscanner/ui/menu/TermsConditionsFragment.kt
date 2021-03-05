package com.baish.skyscanner.ui.menu

import android.os.Bundle
import com.baish.skyscanner.R
import com.baish.skyscanner.data.common.BaseFragment
import com.baish.skyscanner.databinding.FaqLayoutBinding
import com.baish.skyscanner.databinding.TermsconditionsLayoutBinding

class TermsConditionsFragment : BaseFragment(R.layout.termsconditions_layout) {
    var binding : TermsconditionsLayoutBinding? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = TermsconditionsLayoutBinding.inflate(layoutInflater)
    }
}