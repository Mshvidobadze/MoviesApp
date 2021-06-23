package com.example.moviesapp.model.api

import retrofit2.http.GET
import retrofit2.http.Path
import io.reactivex.Single
import com.example.moviesapp.model.values.TVSeriesDetails

interface TMDBInterface {


    //Retrofit for a single tv series
    //https://api.themoviedb.org/3/tv/{tv_id}?api_key=0051467f0e3dccd84a9772a36ad491e0&language=en-US
    @GET("tv/{tv_id}")
    fun getTVSeriesDetails(@Path("tv_id") id: Int): Single<TVSeriesDetails>

    //https://api.themoviedb.org/3/tv/popular?api_key=0051467f0e3dccd84a9772a36ad491e0&language=en-US&page=1
}