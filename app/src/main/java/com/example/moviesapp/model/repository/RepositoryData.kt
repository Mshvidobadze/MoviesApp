package com.example.moviesapp.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviesapp.model.api.TMDBInterface
import com.example.moviesapp.model.values.TVSeriesDetails
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
//
//const val TAG = "RepositoryData"

class RepositoryData (private val TMDBInterface: TMDBInterface, private val compositeDisposable: CompositeDisposable) {

    private val repositoryState = MutableLiveData<StateStatus>()
    val localRepositoryState: LiveData<StateStatus>
        get() = repositoryState

    private val fetchedRepositoryState = MutableLiveData<TVSeriesDetails>()
    val localFetchedRepositoryState: LiveData<TVSeriesDetails>
        get() = fetchedRepositoryState

    fun getTVSeriesData(TVSeriesId:Int){

        repositoryState.postValue(StateStatus.RUNNING)

        try {
            compositeDisposable.add(
                    TMDBInterface.getTVSeriesDetails(TVSeriesId)
                            .subscribeOn(Schedulers.io())
                            .subscribe(
                                    {

                                        fetchedRepositoryState.postValue(it)
                                        repositoryState.postValue(StateStatus.SUCCESS)
                                    },
                                    {
                                        repositoryState.postValue(StateStatus.ERROR)
                                    }
                            )
            )


        }catch (e: Exception){
            Log.e("RepositoryData", e.message.toString())
        }

    }


}