package com.baish.skyscanner.ui.main.video

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.baish.skyscanner.R
import com.baish.skyscanner.databinding.FragmentMainBinding
import com.baish.skyscanner.databinding.VideoLayoutBinding
import com.baish.skyscanner.utils.setCornerRadius

class VideoFragment : Fragment() {
    var binding: VideoLayoutBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VideoLayoutBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cutImages()
        setupClicks()
    }

    private fun setupClicks() {
        binding?.constrWe?.setOnClickListener {
            val intent = Intent(activity, VideoActivity::class.java)
            intent.putExtra("video1",getString(R.string.we))
            startActivity(intent)
        }
        binding?.constrLaser?.setOnClickListener {
            val intent = Intent(activity, VideoActivity::class.java)
            intent.putExtra("video2",getString(R.string.laser))
            startActivity(intent)
        }

        binding?.constrState?.setOnClickListener {
            val intent = Intent(activity, VideoActivity::class.java)
            intent.putExtra("video3", getString(R.string.state))
            startActivity(intent)
        }
    }

    private fun cutImages() {
        val radius = resources.getDimension(R.dimen.mtrl_btn_corner_radius)
        binding?.imageWe?.setCornerRadius(
            topRight = radius,
            topLeft = radius,
            bottomRight = radius,
            bottomLeft = radius
        )
        binding?.imageLaser?.setCornerRadius(
            topRight = radius,
            topLeft = radius,
            bottomRight = radius,
            bottomLeft = radius
        )
        binding?.imageState?.setCornerRadius(
            topRight = radius,
            topLeft = radius,
            bottomRight = radius,
            bottomLeft = radius
        )
        binding?.imageCorona?.setCornerRadius(
            topRight = radius,
            topLeft = radius,
            bottomRight = radius,
            bottomLeft = radius
        )
    }
}
