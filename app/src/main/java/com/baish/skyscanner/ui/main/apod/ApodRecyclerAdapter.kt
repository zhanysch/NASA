package com.baish.skyscanner.ui.main.apod

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.baish.skyscanner.R
import com.baish.skyscanner.data.model.nasa.imageofday.ImageOfTheDayModel
import com.baish.skyscanner.databinding.ApodItemBinding

class ApodRecyclerAdapter : ListAdapter<ImageOfTheDayModel, ApodViewHolder>(diffUtil) {



    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<ImageOfTheDayModel>(){
            override fun areItemsTheSame(oldItem: ImageOfTheDayModel, newItem: ImageOfTheDayModel, ): Boolean {
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

class ApodViewHolder(private val binding : ApodItemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(){

    }

    companion object {
        fun create(parent: ViewGroup) : ApodViewHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.apod_item,parent,false)
            val binding = ApodItemBinding.bind(view)
            return ApodViewHolder(binding)
        }
    }
}

