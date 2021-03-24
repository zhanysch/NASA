package com.baish.skyscanner.ui.menu.features

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.baish.skyscanner.R
import com.baish.skyscanner.data.model.nasa.imageofday.ImageOfTheDayModel
import com.baish.skyscanner.databinding.FeaturesApoditemBinding
import com.squareup.picasso.Picasso

class FeaturesApodAdapter(private val vm: FeaturesViewModel) :
    ListAdapter<ImageOfTheDayModel, FeaturApodVH>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturApodVH {
        return FeaturApodVH.create(parent, vm)
    }

    override fun onBindViewHolder(holder: FeaturApodVH, position: Int) {
        holder.bind(getItem(position))
    }


    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ImageOfTheDayModel>() {
            override fun areItemsTheSame(
                oldItem: ImageOfTheDayModel,
                newItem: ImageOfTheDayModel,
            ): Boolean {
                return oldItem.title == newItem.title &&
                        oldItem.explanation == newItem.explanation &&
                        oldItem.url == newItem.url &&
                        oldItem.isChecked == newItem.isChecked
            }

            override fun areContentsTheSame(
                oldItem: ImageOfTheDayModel,
                newItem: ImageOfTheDayModel,
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}

class FeaturApodVH(
    private val binding: FeaturesApoditemBinding,
    private val vm: FeaturesViewModel,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ImageOfTheDayModel) {
        binding.apodTitle.text = item.title
        binding.apodExplanation.text = item.explanation
        Picasso.get().load(item.url).placeholder(R.drawable.nasa_place).into(binding.apoditemImage)

        binding.checkApod.setOnCheckedChangeListener { button, isChecked ->
            item.isChecked = isChecked
            vm.updateApod(item)
        }
    }

    companion object {
        fun create(parent: ViewGroup, vm: FeaturesViewModel): FeaturApodVH {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.features_apoditem, parent, false)
            val binding = FeaturesApoditemBinding.bind(view)
            return FeaturApodVH(binding, vm)
        }
    }
}