package com.isaquecoelho.simplemovieapp.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.isaquecoelho.simplemovieapp.model.MovieItem
import com.isaquecoelho.simplemovieapp.repository.repository.CatalogRepository

class CatalogViewModel(application: Application) : AndroidViewModel(application) {

    private var movieList: LiveData<MutableList<MovieItem>>? = null

    init {
        val repository = CatalogRepository(application)

        if( checkConnnection() ){
            repository.updateDataFromApi()
        }

        movieList = repository.getMovieList()
    }

    fun getMovieList(): LiveData<MutableList<MovieItem>>? {
        return movieList
    }

    private fun checkConnnection(): Boolean{
        var connection = false
        val connectivyManager = getApplication<Application>().getSystemService(Context.CONNECTIVITY_SERVICE)

        if(connectivyManager is ConnectivityManager){
            val networkInfo = connectivyManager.activeNetworkInfo
            connection = networkInfo.isConnected
        }

        return connection
    }

}
