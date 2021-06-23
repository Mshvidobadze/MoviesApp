package com.example.moviesapp.tvSeries

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.model.repository.StateStatus
import com.example.moviesapp.model.values.TVSeriesDetails
import io.reactivex.disposables.CompositeDisposable

class TVSeriesViewModel (TVSeriesId: Int, private val TVSeriesRepository : TVSeriesDetailsRepository): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val mTVSeriesDetails : LiveData<TVSeriesDetails> by lazy {
        TVSeriesRepository.getSingleTVSeriesData(TVSeriesId,compositeDisposable)

    }



    val mStateStatus : LiveData<StateStatus> by lazy {
        TVSeriesRepository.getDownloadStatus()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}