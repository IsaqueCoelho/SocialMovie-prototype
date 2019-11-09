package com.isaquecoelho.simplemovieapp.repository.remote

import com.isaquecoelho.simplemovieapp.model.Movie
import com.isaquecoelho.simplemovieapp.util.ConstantUtils
import retrofit2.Call
import retrofit2.http.GET

interface MovieApi {

    @GET(ConstantUtils.GET_POPULAR_MOVIES_API + ConstantUtils.PARAM_MOVIEDB_TOKEN + ConstantUtils.MOVIEDB_LANGUAGE)
    fun getMovieList(): Call<Movie>
}