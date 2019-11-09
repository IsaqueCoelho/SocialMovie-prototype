package com.isaquecoelho.simplemovieapp.repository.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.isaquecoelho.simplemovieapp.model.Movie
import com.isaquecoelho.simplemovieapp.model.MovieItem
import com.isaquecoelho.simplemovieapp.repository.local.AppDatabase
import com.isaquecoelho.simplemovieapp.repository.local.dao.MovieDao
import com.isaquecoelho.simplemovieapp.repository.remote.MovieApi
import com.isaquecoelho.simplemovieapp.util.ConstantUtils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class CatalogRepository(application: Application) {

    private val LOG_TAG = CatalogRepository::class.java.simpleName
    private var mMovieDao: MovieDao? = null
    private var movieList: LiveData<MutableList<MovieItem>>? = null

    init {
        val database = AppDatabase.getInstance(application)
        mMovieDao = database.movieDao()
        movieList = mMovieDao!!.getMovieList()

        GlobalScope.launch {
            deleteMovieList()
            insertMovieList( fakeMovieList() )
        }
    }

    fun getMovieList(): LiveData<MutableList<MovieItem>>? {
        return movieList
    }

    fun insertMovieList(movieList: List<MovieItem>){
        mMovieDao!!.insert(movieList)
    }

    fun deleteMovieList(){
        mMovieDao!!.deleteAll()
    }

    fun updateDataFromApi(){
        val retrofit = buildRetrofit()
        val movieApi = retrofit.create<MovieApi>(MovieApi::class.java)

        movieApi.getMovieList().enqueue(object: Callback<Movie> {
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.e(LOG_TAG, "Ops, something went wrong")
                Log.e(LOG_TAG, "error: ${t.message}")
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                when(response.code()){
                    200 -> {
                        if( response.body() != null ){
                            /*val movieList: MutableList<Movie> = ArrayList()
                            movieList.addAll(response.body()!!)*/

                            GlobalScope.launch {
                                deleteMovieList()
                                insertMovieList( response.body()!!.movieList )
                            }
                        }
                    }
                }
            }

        })
    }

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ConstantUtils.MOVIEDB_WEBSERVICE)
            .build()
    }

    private fun fakeMovieList(): MutableList<MovieItem> {
        val movieList: MutableList<MovieItem> = ArrayList()

        movieList.add( MovieItem(297761,
            "Suicide Squad",
            "2016-08-03",
            5.91F,
            "/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg",
            "/ndlQ2Cuc3cjTL7lTynw6I4boP4S.jpg",
            "From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.") )

        return movieList
    }
}
