package com.baish.skyscanner.ui.main.videoandimage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baish.skyscanner.data.interactor.NasaInteractor
import com.baish.skyscanner.data.model.nasa.imageofday.ImageOfTheDayModel
import com.baish.skyscanner.data.model.nasa.nasaimage.Collection
import com.baish.skyscanner.data.model.nasa.nasaimage.Items
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ImageViewModel(private val service : NasaInteractor) : ViewModel(){


    val search  = MutableLiveData<Collection>()
    val image = MutableLiveData<List<Items>>()


    fun startSearch(query: String){
        viewModelScope.launch {
            kotlin.runCatching {
                /*val imageChek = service.getImageandVideoLibrary(title = query)
                if (imageChek.isSuccessful) image.postValue(imageChek.body())*/
            }.onFailure {
                Log.d("sdgsdg","dgdgf")
            }
        }
    }
}
