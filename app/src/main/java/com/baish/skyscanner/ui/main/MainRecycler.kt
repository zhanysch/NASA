package com.baish.skyscanner.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.baish.skyscanner.R
import com.baish.skyscanner.data.model.nasa.imageofday.ImageOfTheDayModel
import com.baish.skyscanner.databinding.MainRecycleritemBinding
import com.baish.skyscanner.utils.setCornerRadius
import com.squareup.picasso.Picasso

class MainRecycler(private val listener: ()-> Unit) : ListAdapter<ImageOfTheDayModel, MainViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder.create(parent,listener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
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
                        oldItem.url == newItem.url
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

class MainViewHolder(private val binding: MainRecycleritemBinding, private val listener: () -> Unit) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ImageOfTheDayModel) {

        binding.recyclermaintext.text = item.title
        Picasso.get().load(item.url).placeholder(R.drawable.nasa_place)
            .into(binding.recyclermainimage)

        val radius = itemView.context.resources.getDimension(R.dimen.mtrl_slider_thumb_radius)
        binding.recyclermainimage.setCornerRadius(
            topRight = radius,
            topLeft = radius,
            bottomRight = radius,
            bottomLeft = radius
        )

        binding.parentView.setOnClickListener {
            listener.invoke()
        }
    }

    companion object {
        fun create(parent: ViewGroup, listener: () -> Unit): MainViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.main_recycleritem, parent, false)
            val binding = MainRecycleritemBinding.bind(view)
            return MainViewHolder(binding,listener)
        }
    }
}