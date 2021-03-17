package com.baish.skyscanner.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.baish.skyscanner.databinding.FeaturesLayoutBinding
import com.baish.skyscanner.databinding.FragmentMainBinding

class FeaturesFragment : Fragment(){
    var binding: FeaturesLayoutBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FeaturesLayoutBinding.inflate(layoutInflater)

        return binding?.root
    }
}