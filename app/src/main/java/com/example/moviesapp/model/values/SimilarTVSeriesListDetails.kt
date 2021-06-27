package com.example.moviesapp.model.values


import com.google.gson.annotations.SerializedName

data class SimilarTVSeriesListDetails(
        @SerializedName("page")
    val page: Int,
        @SerializedName("results")
    val results: List<SimilarTVSeriesDetails>,
        @SerializedName("total_pages")
    val totalPages: Int,
        @SerializedName("total_results")
    val totalResults: Int
)