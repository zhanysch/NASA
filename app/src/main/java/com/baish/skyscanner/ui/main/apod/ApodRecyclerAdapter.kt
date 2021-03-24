package com.baish.skyscanner.ui.main.apod

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.baish.skyscanner.R
import com.baish.skyscanner.data.model.nasa.imageofday.ImageOfTheDayModel
import com.baish.skyscanner.databinding.ApodItemBinding
import com.baish.skyscanner.ui.main.mars.MarsViewModel
import com.squareup.picasso.Picasso

class ApodRecyclerAdapter(private val vm : ApodViewModel) : androidx.recyclerview.widget.ListAdapter<ImageOfTheDayModel, ApodViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApodViewHolder {
       return ApodViewHolder.create(parent,vm)
    }

    override fun onBindViewHolder(holder: ApodViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<ImageOfTheDayModel>(){
            override fun areItemsTheSame(oldItem: ImageOfTheDayModel, newItem: ImageOfTheDayModel, ): Boolean {
                return oldItem.title == newItem.title &&
                        oldItem.explanation == newItem.explanation &&
                        oldItem.url == newItem.url&&
                        oldItem.isChecked == newItem.isChecked
            }

            override fun areContentsTheSame(oldItem: ImageOfTheDayModel, newItem: ImageOfTheDayModel, ): Boolean {
                return oldItem == newItem
            }

        }
    }

}

class ApodViewHolder(private val binding : ApodItemBinding,private val vm : ApodViewModel) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: ImageOfTheDayModel) {
       binding.apodTitle.text = item.title
        binding.apodExplanation.text = item.explanation
        Picasso.get().load(item.url).placeholder(R.drawable.nasa_place).into(binding.apoditemImage)

        binding.checkApod.setOnCheckedChangeListener { buttonView, isChecked ->
           item.isChecked = isChecked
            vm.updateApod(item)
        }
    }

    companion object {
        fun create(parent: ViewGroup, vm: ApodViewModel) : ApodViewHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.apod_item,parent,false)
            val binding = ApodItemBinding.bind(view)
            return ApodViewHolder(binding,vm)
        }
    }
}

