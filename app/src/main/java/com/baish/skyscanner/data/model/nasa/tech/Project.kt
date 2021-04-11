package com.baish.skyscanner.data.model.nasa.tech

import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2021 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class Project (

    @SerializedName("projectId") val projectId : Int,
    @SerializedName("title") val title : String,
    @SerializedName("primaryTaxonomyNodes") val primaryTaxonomyNodes : List<PrimaryTaxonomyNodes>,
    @SerializedName("startTrl") val startTrl : Int,
    @SerializedName("currentTrl") val currentTrl : Int,
    @SerializedName("endTrl") val endTrl : Int,
    @SerializedName("benefits") val benefits : String,
    @SerializedName("description") val description : String,
    @SerializedName("startYear") val startYear : Int,
    @SerializedName("startMonth") val startMonth : Int,
    @SerializedName("endYear") val endYear : Int,
    @SerializedName("endMonth") val endMonth : Int,
    @SerializedName("statusDescription") val statusDescription : String,
    @SerializedName("principalInvestigators") val principalInvestigators : List<PrincipalInvestigators>,
    @SerializedName("programDirectors") val programDirectors : List<ProgramDirectors>,
    @SerializedName("programExecutives") val programExecutives : List<ProgramExecutives>,
    @SerializedName("programManagers") val programManagers : List<ProgramManagers>,
    @SerializedName("projectManagers") val projectManagers : List<ProjectManagers>,
    @SerializedName("libraryItems") val libraryItems : List<LibraryItems>,
    @SerializedName("primaryImage") val primaryImage : PrimaryImage,
    @SerializedName("responsibleMd") val responsibleMd : ResponsibleMd,
    @SerializedName("program") val program : Program,
    @SerializedName("leadOrganization") val leadOrganization : LeadOrganization,
    @SerializedName("supportingOrganizations") val supportingOrganizations : List<SupportingOrganizations>,
    @SerializedName("closeoutDocuments") val closeoutDocuments : List<CloseoutDocuments>,
    @SerializedName("lastUpdated") val lastUpdated : String,
    @SerializedName("releaseStatusString") val releaseStatusString : String,
    @SerializedName("endDateString") val endDateString : String,
    @SerializedName("startDateString") val startDateString : String
)