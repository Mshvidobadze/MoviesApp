package com.example.moviesapp.tvSeries

import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.moviesapp.model.api.TMDBInterface
import com.example.moviesapp.model.repository.RepositoryData
import com.example.moviesapp.model.repository.StateStatus
import com.example.moviesapp.model.values.TVSeriesDetails
import io.reactivex.disposables.CompositeDisposable

class TVSeriesDetailsRepository(private val TMDBInterface : TMDBInterface) {

    lateinit var mTVSeriesRepositoryData: RepositoryData


    fun getSingleTVSeriesData (TVSeriesId: Int, compositeDisposable: CompositeDisposable) : LiveData<TVSeriesDetails> {

        mTVSeriesRepositoryData = RepositoryData(TMDBInterface,compositeDisposable)
        mTVSeriesRepositoryData.getTVSeriesData(TVSeriesId)

        return mTVSeriesRepositoryData.localFetchedRepositoryState

    }


    fun getDownloadStatus(): LiveData<StateStatus> {
        return mTVSeriesRepositoryData.localRepositoryState
    }



}