package com.baish.skyscanner.ui.main.techproject

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baish.skyscanner.data.interactor.NasaInteractor
import com.baish.skyscanner.data.model.nasa.tech.Files
import com.baish.skyscanner.data.model.nasa.tech.LeadOrganization
import kotlinx.coroutines.launch

class TechProjectViewModel(private val service: NasaInteractor) : ViewModel() {

    val project = MutableLiveData<com.baish.skyscanner.data.model.nasa.tech.Project>()
    val projectFiles = MutableLiveData<LeadOrganization>()
    val files = MutableLiveData<Files>()


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