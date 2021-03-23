package com.baish.skyscanner.ui.main.videoandimage.details

import android.media.Image
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.baish.skyscanner.R
import com.baish.skyscanner.databinding.ImagedetailsLayoutBinding
import com.squareup.picasso.Picasso

class ImageDetails: Fragment() {


    private val args: ImageDetailsArgs by navArgs()
    var binding: ImagedetailsLayoutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = ImagedetailsLayoutBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding?.imageDetails?.transitionName =
        resources.getString(R.string.image_transition, args.details?.id)

        val image = args.details?.links?.first()?.href
        Picasso.get().load(image).placeholder(R.drawable.nasa_place).into(binding?.imageDetails)

        val itemTwo = args.details?.data?.first()?.title
         binding?.detailsTitle?.text = itemTwo

        val itemDes = args.details?.data?.first()?.description
        binding?.detailsExplanation?.text = itemDes
    }
}