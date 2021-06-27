package com.example.moviesapp.model.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.moviesapp.model.api.PAGE
import com.example.moviesapp.model.api.TMDBInterface
import com.example.moviesapp.model.values.SimilarTVSeriesDetails
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SimilarRepositoryData(private val tvSeriesId: Int, private val TMDBInterface: TMDBInterface, private val compositeDisposable: CompositeDisposable): PageKeyedDataSource<Int, SimilarTVSeriesDetails>()  {

    private var page = PAGE
    val repositoryState: MutableLiveData<RepositoryState> = MutableLiveData()

    override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, SimilarTVSeriesDetails>) {

        repositoryState.postValue(RepositoryState.RUNNING)
        compositeDisposable.add(
                TMDBInterface.getSimilarTVSeriesDetails(tvSeriesId,page)
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                {
                                    callback.onResult(it.results, null, page+1)
                                    repositoryState.postValue(RepositoryState.SUCCESS)
                                },
                                {
                                    repositoryState.postValue(RepositoryState.ERROR)
                                }
                        )
        )

    }

    override fun loadAfter(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, SimilarTVSeriesDetails>) {
        repositoryState.postValue(RepositoryState.RUNNING)
        compositeDisposable.add(
                TMDBInterface.getSimilarTVSeriesDetails(tvSeriesId,params.key)
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                {
                                    callback.onResult(it.results, params.key+1)
                                    repositoryState.postValue(RepositoryState.SUCCESS)

                                },
                                {
                                    repositoryState.postValue(RepositoryState.ERROR)
                                }
                        )
        )

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, SimilarTVSeriesDetails>) {
        TODO("Not yet implemented")
    }


}