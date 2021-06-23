package com.example.moviesapp.model.repository

enum class StateStatus{
    SUCCESS,
    RUNNING,
    ERROR
}

class RepositoryState (val status: StateStatus){

    companion object{

        val SUCCESS: RepositoryState = RepositoryState(StateStatus.SUCCESS)
        val RUNNING: RepositoryState = RepositoryState(StateStatus.RUNNING)
        val ERROR: RepositoryState = RepositoryState(StateStatus.ERROR)

    }
}