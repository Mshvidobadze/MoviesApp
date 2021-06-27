package com.example.moviesapp.model.repository

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.moviesapp.model.api.TMDBInterface
import com.example.moviesapp.model.values.PopularTVSeriesDetails
import io.reactivex.disposables.CompositeDisposable

class PopularRepositorySource(private val type: String,
                              private val TMDBInterface: TMDBInterface,
                              private val compositeDisposable: CompositeDisposable)
    :androidx.paging.DataSource.Factory<Int, PopularTVSeriesDetails>() {

    val repositorySource = MutableLiveData<PopularRepositoryData>()



    override fun create(): androidx.paging.DataSource<Int, PopularTVSeriesDetails> {
        val dataSource = PopularRepositoryData(type, TMDBInterface,compositeDisposable)
        repositorySource.postValue(dataSource)
        return dataSource
    }
}