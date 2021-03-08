package com.baish.skyscanner.ui.main

import Files
import LeadOrganization
import Project
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baish.skyscanner.data.interactor.NasaInteractor
import com.baish.skyscanner.data.model.nasa.imageofday.ImageOfTheDayModel
import kotlinx.coroutines.launch

class MainViewModel(private val service : NasaInteractor): ViewModel() {

    val image = MutableLiveData<List<ImageOfTheDayModel>>()


    val project = MutableLiveData<Project>()
    val projectFiles = MutableLiveData<LeadOrganization>()

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

    fun loadProjects(){
        viewModelScope.launch {
            kotlin.runCatching {
                val techProject = service.loadTechProjects(id_parameter = 17792)
                if (techProject.isSuccessful) project.postValue(techProject.body()?.project)
            }.onFailure {
                Log.d("sdgsdg","dgdgf")
            }
        }
    }

}