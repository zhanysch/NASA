package com.baish.skyscanner.ui.main.mars

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.baish.skyscanner.data.model.nasa.mars.Photos
import com.baish.skyscanner.data.repository.NasaRepository
import com.baish.skyscanner.data.repository.NasaRepositoryImpl

class MarsViewModel(private val repository : NasaRepository):ViewModel() {

    @ExperimentalPagingApi
    fun getPagindMarsData(): LiveData<PagingData<Photos>>{
        return repository.getPagingResult()
    }
}