package com.example.moviesapp.popularTVSeries

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviesapp.model.repository.RepositoryState
import com.example.moviesapp.model.values.PopularTVSeriesDetails
import io.reactivex.disposables.CompositeDisposable

class LatestViewModel (private val type: String, private val popularDetailsRepository: PopularDetailsRepository): ViewModel(){

    private val compositeDisposable = CompositeDisposable()

    val mLatestList : LiveData<PagedList<PopularTVSeriesDetails>> by lazy {
        popularDetailsRepository.getPopularTVSeriesData(type, compositeDisposable)

    }

    val  mStateStatus : LiveData<RepositoryState> by lazy {
        popularDetailsRepository.getPopularDownloadStatus()

    }

    fun listIsEmpty(): Boolean {
        return mLatestList.value?.isEmpty() ?: true
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}