package com.baish.skyscanner.ui.menu

import android.os.Bundle
import com.baish.skyscanner.R
import com.baish.skyscanner.data.common.BaseFragment
import com.baish.skyscanner.databinding.FaqLayoutBinding
import com.baish.skyscanner.databinding.PrivacyLayoutBinding

class PrivacyFragment : BaseFragment(R.layout.privacy_layout) {

    var binding : PrivacyLayoutBinding? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = PrivacyLayoutBinding.inflate(layoutInflater)
    }
}