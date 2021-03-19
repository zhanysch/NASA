package com.baish.skyscanner.ui.menu.features

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.baish.skyscanner.data.db.AppDataBase
import com.baish.skyscanner.data.model.nasa.mars.Photos
import com.baish.skyscanner.data.repository.NasaRepository

class FeaturesViewModel(private val repository : NasaRepository, private val db : AppDataBase): ViewModel(){
    @ExperimentalPagingApi
    fun getPagindMarsData(): LiveData<PagingData<Photos>> {
        return repository.getPagingResult()
    }


    @ExperimentalPagingApi
    fun getPagingMarsLikes(): LiveData<List<Photos>>{
        return  repository.getFavouriteMars()
    }

    fun update(item : Photos){
        db.getContentDao().update(item)
    }
}