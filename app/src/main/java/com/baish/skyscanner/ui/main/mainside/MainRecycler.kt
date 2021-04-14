package com.baish.skyscanner.ui.main.mainside

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

class MainRecycler(private val listener: (position:Int)-> Unit) : ListAdapter<ImageOfTheDayModel, MainViewHolder>(
    diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder.create(parent, listener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position),position)
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

            override fun areContentsTheSame(oldItem: ImageOfTheDayModel, newItem: ImageOfTheDayModel, ): Boolean {
                return oldItem == newItem
            }

        }
    }
}

class MainViewHolder(private val binding: MainRecycleritemBinding, private val listener: (position: Int) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ImageOfTheDayModel, position: Int) {

        binding.recyclermaintext.text = item.title
        Picasso.get().load(item.url).placeholder(R.drawable.nasa_place)
            .into(binding.recyclermainimage)

        val radius = itemView.context.resources.getDimension(R.dimen.mtrl_btn_corner_radius)
        binding.recyclermainimage.setCornerRadius(
            topRight = radius,
            topLeft = radius,
            bottomRight = radius,
            bottomLeft = radius
        )

        binding.parentView.setOnClickListener {
            listener.invoke(position)
        }
    }

    companion object {
        fun create(parent: ViewGroup, listener: (position: Int) -> Unit): MainViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.main_recycleritem, parent, false)
            val binding = MainRecycleritemBinding.bind(view)
            return MainViewHolder(binding,listener)
        }
    }
}