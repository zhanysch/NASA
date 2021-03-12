package com.baish.skyscanner.data.model.nasa.techproject

import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2021 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class Project (

	@SerializedName("id") val id : Int,
	@SerializedName("lastUpdated") val lastUpdated : String,
	@SerializedName("title") val title : String,
	@SerializedName("status") val status : String,
	@SerializedName("startDate") val startDate : String,
	@SerializedName("endDate") val endDate : String,
	@SerializedName("description") val description : String,
	@SerializedName("benefits") val benefits : String,
	@SerializedName("technologyMaturityStart") val technologyMaturityStart : Int,
	@SerializedName("technologyMaturityCurrent") val technologyMaturityCurrent : Int,
	@SerializedName("technologyMaturityEnd") val technologyMaturityEnd : Int,
	@SerializedName("responsibleProgram") val responsibleProgram : String,
	@SerializedName("responsibleMissionDirectorateOrOffice") val responsibleMissionDirectorateOrOffice : String,
	@SerializedName("leadOrganization") val leadOrganization : LeadOrganization,
	@SerializedName("workLocations") val workLocations : List<String>,
	@SerializedName("programDirectors") val programDirectors : List<String>,
	@SerializedName("programManagers") val programManagers : List<String>,
	@SerializedName("projectManagers") val projectManagers : List<String>,
	@SerializedName("principalInvestigators") val principalInvestigators : List<String>,
	@SerializedName("libraryItems") val libraryItems : List<LibraryItems>,
	@SerializedName("closeoutDocuments") val closeoutDocuments : List<String>,
	@SerializedName("supportingOrganizations") val supportingOrganizations : List<SupportingOrganizations>,
	@SerializedName("primaryTas") val primaryTas : List<PrimaryTas>,
	@SerializedName("additionalTas") val additionalTas : List<String>
)