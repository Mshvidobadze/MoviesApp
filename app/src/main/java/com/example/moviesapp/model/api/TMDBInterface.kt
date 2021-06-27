package com.example.moviesapp.model.api

import retrofit2.http.GET
import retrofit2.http.Path
import io.reactivex.Single
import com.example.moviesapp.model.values.TVSeriesDetails
import com.example.moviesapp.model.values.PopularTVSeriesListDetails
import com.example.moviesapp.model.values.SimilarTVSeriesListDetails
import retrofit2.http.Query

interface TMDBInterface {


    //Retrofit for a single tv series
    //https://api.themoviedb.org/3/tv/{tv_id}?api_key=0051467f0e3dccd84a9772a36ad491e0&language=en-US
    @GET("tv/{tv_id}")
    fun getTVSeriesDetails(@Path("tv_id") id: Int): Single<TVSeriesDetails>

    //Retrofit for popular tv series
    //https://api.themoviedb.org/3/tv/popular?api_key=0051467f0e3dccd84a9772a36ad491e0&language=en-US&page=1
    @GET("tv/popular")
    fun getPopularTVSeriesDetails(@Query("page")page: Int): Single<PopularTVSeriesListDetails>

    //Retrofit for similar tv series
    //https://api.themoviedb.org/3/tv/84958/similar?api_key=0051467f0e3dccd84a9772a36ad491e0&language=en-US&page=1
    @GET("tv/{tv_id}/similar")
    fun getSimilarTVSeriesDetails(@Path("tv_id") id: Int, @Query("page")page: Int): Single<SimilarTVSeriesListDetails>

    //Retrofit for latest tv series
    //https://api.themoviedb.org/3/tv/popular?api_key=0051467f0e3dccd84a9772a36ad491e0&language=en-US&page=1
    @GET("tv/latest")
    fun getLatestTVSeriesDetails(@Query("page")page: Int): Single<PopularTVSeriesListDetails>

    //Retrofit for latest tv series
    //https://api.themoviedb.org/3/tv/popular?api_key=0051467f0e3dccd84a9772a36ad491e0&language=en-US&page=1
    @GET("tv/airing_today")
    fun getAirTodayTVSeriesDetails(@Query("page")page: Int): Single<PopularTVSeriesListDetails>

    //Retrofit for latest tv series
    //https://api.themoviedb.org/3/tv/popular?api_key=0051467f0e3dccd84a9772a36ad491e0&language=en-US&page=1
    @GET("tv/on_the_air")
    fun getAirTVSeriesDetails(@Query("page")page: Int): Single<PopularTVSeriesListDetails>

    //Retrofit for latest tv series
    //https://api.themoviedb.org/3/tv/popular?api_key=0051467f0e3dccd84a9772a36ad491e0&language=en-US&page=1
    @GET("tv/top_rated")
    fun getTopTVSeriesDetails(@Query("page")page: Int): Single<PopularTVSeriesListDetails>

}