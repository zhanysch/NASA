package com.baish.skyscanner.ui.menu.features

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.baish.skyscanner.data.db.AppDataBase
import com.baish.skyscanner.data.model.nasa.imageofday.ImageOfTheDayModel
import com.baish.skyscanner.data.model.nasa.mars.Photos
import com.baish.skyscanner.data.repository.NasaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeaturesViewModel(private val repository : NasaRepository, private val db : AppDataBase): ViewModel(){


    val data = MutableLiveData<List<Photos>>()

    fun showFavourite(photos: List<Photos>?) {
        viewModelScope.launch(Dispatchers.Default){
            kotlin.runCatching {
                val favourite = db.getContentDao().getFavoriteMars()
                photos?.forEach { item ->
                    if (favourite.find { it.id == item.id } !=null)
                        item.isChecked = true
                }
                photos?.let { data.postValue(it) }
            }.onFailure {

            }
        }

    }

    @ExperimentalPagingApi
    fun getPagingMarsLikes(): LiveData<List<Photos>>{
        return  repository.getFavouriteMars()
    }

    fun getLikesApod(): LiveData<List<ImageOfTheDayModel>>{
         return repository.getFavouriteApod()
    }
                             //FavouritePhotos
    fun update(item : Photos){
        db.getContentDao().update(item)  // or insert or Delete
    }

    fun updateApod(item:ImageOfTheDayModel){
        db.getContentDao().updateApod(item)
    }
}