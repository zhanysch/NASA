package com.baish.skyscanner.ui.menu.features

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.baish.skyscanner.R
import com.baish.skyscanner.data.model.nasa.mars.Photos
import com.baish.skyscanner.data.model.nasa.mars.PhotosFavourite
import com.baish.skyscanner.databinding.FeaturesMarsitemBinding
import com.baish.skyscanner.ui.main.mars.MarsViewModel
import com.squareup.picasso.Picasso

class FeaturMarsAdapter(private val vm : FeaturesViewModel): ListAdapter<PhotosFavourite,FeaturMarsViewHolder>(photos_diff) {

    override fun onBindViewHolder(holder: FeaturMarsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturMarsViewHolder {
        return FeaturMarsViewHolder.create(parent,vm)
    }


    companion object{
        val photos_diff = object : DiffUtil.ItemCallback<PhotosFavourite>(){
            override fun areItemsTheSame(oldItem: PhotosFavourite, newItem: PhotosFavourite): Boolean {
                return oldItem.img_src == newItem.img_src &&
                        oldItem.earth_date == newItem.earth_date &&
                        oldItem.camera == newItem.camera &&
                        oldItem.isChecked == newItem.isChecked
            }

            override fun areContentsTheSame(oldItem: PhotosFavourite, newItem: PhotosFavourite): Boolean {
                return oldItem == newItem
            }

        }
    }


}

class FeaturMarsViewHolder(private val binding : FeaturesMarsitemBinding,private val vm : FeaturesViewModel):RecyclerView.ViewHolder(binding.root){
    fun bind(item: PhotosFavourite?) {

        binding.marsTitleTwo.text = item?.camera?.full_name
        binding.marsDateWord.text = item?.earth_date
        binding.landingDateTwo.text = item?.rover?.landing_date
        binding.launchDateTwo.text = item?.rover?.launch_date
        binding.statusTwo.text = item?.rover?.status

        binding.checkMars.isChecked = item?.isChecked ?: false


        Picasso.get().load(item?.img_src).placeholder(R.drawable.nasa_place).into(binding.marsitemImage)

        binding.checkMars.setOnClickListener {
            item?.isChecked = !(item?.isChecked?:true)
            item?.let { vm.update(it) }
        }
        if (item != null) {
            binding.checkMars.isChecked = item.isChecked
        }

    }

    companion object {
        fun create(parent: ViewGroup, vm: FeaturesViewModel, ) : FeaturMarsViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.features_marsitem,parent,false)
            val binding = FeaturesMarsitemBinding.bind(view)
            return  FeaturMarsViewHolder(binding,vm)
        }
    }
}