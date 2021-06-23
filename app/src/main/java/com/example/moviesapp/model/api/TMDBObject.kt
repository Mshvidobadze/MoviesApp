package com.example.moviesapp.model.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

const val KEY = "0051467f0e3dccd84a9772a36ad491e0"
const val URL = "https://api.themoviedb.org/3/"
const val POSTER_URL = "https://image.tmdb.org/t/p/w342"

object TMDBObject {

    fun getTMDBObject() : TMDBInterface{

        val requestInterceptor = Interceptor{ chain ->

            val url = chain.request().url().newBuilder().addQueryParameter("key", KEY).build()

            val request = chain.request().newBuilder().url(url).build()

            return@Interceptor chain.proceed(request)
        }

        val okHttpClient : OkHttpClient = OkHttpClient.Builder().addInterceptor(requestInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS).build()

        return Retrofit.Builder().client(okHttpClient)
                .baseUrl(URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TMDBInterface::class.java)

    }

}