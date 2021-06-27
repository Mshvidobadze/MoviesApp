package com.example.moviesapp.similarTVSeries

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviesapp.model.api.TMDBInterface
import com.example.moviesapp.model.api.VIEW_COUNT
import com.example.moviesapp.model.repository.*
import com.example.moviesapp.model.values.SimilarTVSeriesDetails
import io.reactivex.disposables.CompositeDisposable

class SimilarDetailsRepository (private val TMDBInterface : TMDBInterface) {
    lateinit var tvSeriesList: LiveData<PagedList<SimilarTVSeriesDetails>>
    lateinit var similarRepositorySource: SimilarRepositorySource

    fun getSimilarTVSeriesData (tvSeriesId:Int, compositeDisposable: CompositeDisposable) : LiveData<PagedList<SimilarTVSeriesDetails>> {

        similarRepositorySource = SimilarRepositorySource(tvSeriesId, TMDBInterface, compositeDisposable)
        val config = PagedList.Config.Builder().setEnablePlaceholders(false).setPageSize(VIEW_COUNT).build()

        tvSeriesList = LivePagedListBuilder(similarRepositorySource, config).build()




        return tvSeriesList

    }

    fun getSimilarDownloadStatus(): LiveData<RepositoryState> {
        return Transformations.switchMap<SimilarRepositoryData, RepositoryState>(
                similarRepositorySource.repositorySource, SimilarRepositoryData::repositoryState
        )
    }
}