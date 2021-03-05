package com.baish.skyscanner.ui.main.apod

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baish.skyscanner.data.interactor.NasaInteractor
import com.baish.skyscanner.data.model.nasa.imageofday.ImageOfTheDayModel
import kotlinx.coroutines.launch

class ApodViewModel(private val service : NasaInteractor): ViewModel() {

    val image = MutableLiveData<List<ImageOfTheDayModel>>()

    fun loadImage(){
        viewModelScope.launch {
            runCatching {
                val result = service.loadImagesOfDay(count = 50,thumbs = false)
                if (result.isSuccessful) image.postValue(result.body())
            }.onFailure {
                Log.d("fsdgsdgs","gsdgsgsgsdg")
            }
        }
    }
}