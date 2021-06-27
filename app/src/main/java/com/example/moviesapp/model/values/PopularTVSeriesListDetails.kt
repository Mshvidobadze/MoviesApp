package com.example.moviesapp.model.values


import com.google.gson.annotations.SerializedName

data class PopularTVSeriesListDetails(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<PopularTVSeriesDetails>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)