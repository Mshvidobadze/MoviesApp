package com.example.moviesapp.similarTVSeries

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviesapp.model.repository.RepositoryState
import com.example.moviesapp.model.values.SimilarTVSeriesDetails
import io.reactivex.disposables.CompositeDisposable

class SimilarViewModel(private val tvSeriesId: Int, private val similarDetailsRepository:SimilarDetailsRepository): ViewModel(){

    private val compositeDisposable = CompositeDisposable()

    val mSimilarList : LiveData<PagedList<SimilarTVSeriesDetails>> by lazy {
        similarDetailsRepository.getSimilarTVSeriesData(tvSeriesId, compositeDisposable)

    }



    val  mStateStatus : LiveData<RepositoryState> by lazy {
        similarDetailsRepository.getSimilarDownloadStatus()

    }

    fun listIsEmpty(): Boolean {
        return mSimilarList.value?.isEmpty() ?: true
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}