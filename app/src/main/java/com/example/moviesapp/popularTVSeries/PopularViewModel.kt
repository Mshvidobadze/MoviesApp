package com.example.moviesapp.popularTVSeries

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviesapp.model.repository.RepositoryState
import com.example.moviesapp.model.repository.StateStatus
import com.example.moviesapp.model.values.PopularTVSeriesDetails
import com.example.moviesapp.model.values.TVSeriesDetails
import com.example.moviesapp.tvSeries.TVSeriesDetailsRepository
import io.reactivex.disposables.CompositeDisposable

class PopularViewModel (private val type: String, private val popularDetailsRepository: PopularDetailsRepository): ViewModel(){

    private val compositeDisposable = CompositeDisposable()

    val mPopularList : LiveData<PagedList<PopularTVSeriesDetails>> by lazy {
        popularDetailsRepository.getPopularTVSeriesData(type, compositeDisposable)

    }

    val  mStateStatus : LiveData<RepositoryState> by lazy {
        popularDetailsRepository.getPopularDownloadStatus()

    }

    fun listIsEmpty(): Boolean {
        return mPopularList.value?.isEmpty() ?: true
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}