package com.example.moviesapp.model.repository

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.moviesapp.model.api.TMDBInterface
import com.example.moviesapp.model.values.SimilarTVSeriesDetails
import io.reactivex.disposables.CompositeDisposable

class SimilarRepositorySource (private val tvSeriesId: Int,
                               private val TMDBInterface: TMDBInterface,
                               private val compositeDisposable: CompositeDisposable)
    :androidx.paging.DataSource.Factory<Int, SimilarTVSeriesDetails>() {

    val repositorySource = MutableLiveData<SimilarRepositoryData>()



    override fun create(): androidx.paging.DataSource<Int, SimilarTVSeriesDetails> {
        val dataSource = SimilarRepositoryData(tvSeriesId,TMDBInterface,compositeDisposable)
        repositorySource.postValue(dataSource)

        return dataSource
    }
}