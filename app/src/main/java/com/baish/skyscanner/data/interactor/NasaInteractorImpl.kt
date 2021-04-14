package com.baish.skyscanner.data.interactor

import com.baish.skyscanner.data.model.nasa.imageofday.ImageOfTheDayModel
import com.baish.skyscanner.data.model.nasa.nasaimage.NasaImageandVideo
import com.baish.skyscanner.data.model.nasa.tech.TechModel
import com.baish.skyscanner.data.remote.NasaService
import retrofit2.Response

interface NasaInteractor{
    suspend fun loadImagesOfDay(count : Int, thumbs : Boolean) : Response<List<ImageOfTheDayModel>>
    suspend fun loadTechProjects(id_parameter : Int) : Response <TechModel>
    suspend fun getImageandVideoLibrary(title : String):Response<NasaImageandVideo>


}

class NasaInteractorImpl(private val service : NasaService) : NasaInteractor {
    override suspend fun loadImagesOfDay(count: Int, thumbs: Boolean, ): Response<List<ImageOfTheDayModel>> {
        return service.getImage(count=count,thumbs = thumbs,apiKey = "EnKUTj7VDqVE0Cnu63SCYny69JzzTllJdhzVCmZb")
    }

    override suspend fun loadTechProjects(id_parameter: Int): Response<TechModel> {
        return service.getTechProject(id_parameter = id_parameter, api_key = "EnKUTj7VDqVE0Cnu63SCYny69JzzTllJdhzVCmZb")
    }

    override suspend fun getImageandVideoLibrary(title: String): Response<NasaImageandVideo> {
        return  service.getImagesandVideo("https://images-api.nasa.gov/search",title)
    }

}