package com.baish.skyscanner.ui.main.mainside

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baish.skyscanner.data.interactor.NasaInteractor
import com.baish.skyscanner.data.model.nasa.imageofday.ImageOfTheDayModel
import com.baish.skyscanner.data.repository.NasaRepository
import kotlinx.coroutines.launch

class MainViewModel(private val service : NasaInteractor, private val repository: NasaRepository): ViewModel() {

    val image = MutableLiveData<List<ImageOfTheDayModel>>()


    fun loadItemsMain(){
        viewModelScope.launch {
            runCatching {
                val result = service.loadImagesOfDay(count = 5,thumbs = false)
                if (result.isSuccessful) image.postValue(result.body())
                repository.loadImagesOfDayDB(5 ,false)
            }.onFailure {
                Log.d("fsdgsdgs","gsdgsgsgsdg")
            }
        }
    }

    fun getContentRoomMain(): LiveData<List<ImageOfTheDayModel>> {
        return repository.getImageOfTheDayDb()
    }
}