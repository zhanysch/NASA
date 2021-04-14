package com.baish.skyscanner.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PrivacyLayoutBinding.inflate(layoutInflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btnBack?.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}