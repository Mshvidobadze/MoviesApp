package com.example.moviesapp.popularTVSeries

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviesapp.model.api.TMDBInterface
import com.example.moviesapp.model.api.VIEW_COUNT
import com.example.moviesapp.model.repository.*
import com.example.moviesapp.model.values.PopularTVSeriesDetails
import io.reactivex.disposables.CompositeDisposable

class PopularDetailsRepository (private val TMDBInterface : TMDBInterface){

    lateinit var tvSeriesList: LiveData<PagedList<PopularTVSeriesDetails>>
    lateinit var popularRepositorySource: PopularRepositorySource

    fun getPopularTVSeriesData (type: String, compositeDisposable: CompositeDisposable) : LiveData<PagedList<PopularTVSeriesDetails>> {

        popularRepositorySource = PopularRepositorySource(type, TMDBInterface, compositeDisposable)
        val config = PagedList.Config.Builder().setEnablePlaceholders(false).setPageSize(VIEW_COUNT).build()

        tvSeriesList = LivePagedListBuilder(popularRepositorySource, config).build()

        return tvSeriesList

    }

    fun getPopularDownloadStatus(): LiveData<RepositoryState> {
        return Transformations.switchMap<PopularRepositoryData, RepositoryState>(
                popularRepositorySource.repositorySource, PopularRepositoryData::repositoryState
        )
    }
}