package com.example.moviesapp.model.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.moviesapp.model.api.PAGE
import com.example.moviesapp.model.api.TMDBInterface
import com.example.moviesapp.model.values.PopularTVSeriesDetails
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class PopularRepositoryData (private val type: String, private val TMDBInterface: TMDBInterface, private val compositeDisposable: CompositeDisposable): PageKeyedDataSource<Int, PopularTVSeriesDetails>() {

    private var page = PAGE
    val repositoryState: MutableLiveData<RepositoryState> = MutableLiveData()

    override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, PopularTVSeriesDetails>
    ) {
        repositoryState.postValue(RepositoryState.RUNNING)
        compositeDisposable.clear()
        if(type == "popular"){
            compositeDisposable.add(
                    TMDBInterface.getPopularTVSeriesDetails(page)
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
        if(type == "latest"){
            compositeDisposable.add(
                    TMDBInterface.getLatestTVSeriesDetails(page)
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
        if(type == "airtoday"){
            compositeDisposable.add(
                    TMDBInterface.getAirTodayTVSeriesDetails(page)
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
        if(type == "air"){
            compositeDisposable.add(
                    TMDBInterface.getAirTVSeriesDetails(page)
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
        if(type == "toprated"){
            compositeDisposable.add(
                    TMDBInterface.getTopTVSeriesDetails(page)
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

    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, PopularTVSeriesDetails>
    ) {
        repositoryState.postValue(RepositoryState.RUNNING)

        if(type == "popular"){
            compositeDisposable.add(
                    TMDBInterface.getPopularTVSeriesDetails(params.key)
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
        if(type == "latest"){
            compositeDisposable.add(
                    TMDBInterface.getLatestTVSeriesDetails(params.key)
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
        if(type == "airtoday"){
            compositeDisposable.add(
                    TMDBInterface.getAirTodayTVSeriesDetails(params.key)
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
        if(type == "air"){
            compositeDisposable.add(
                    TMDBInterface.getAirTVSeriesDetails(params.key)
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
        if(type == "toprated"){
            compositeDisposable.add(
                    TMDBInterface.getTopTVSeriesDetails(params.key)
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
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PopularTVSeriesDetails>) {

    }


}