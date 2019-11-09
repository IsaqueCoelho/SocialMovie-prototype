package com.isaquecoelho.simplemovieapp.util

class ConstantUtils {

    companion object{

        const val DATABASE_NAME = "MovieDatabase.db"
        const val DATABASE_VERSION = 1

        const val MOVIEDB_WEBSERVICE = "https://api.themoviedb.org/3/"
        private const val MOVIEDB_TOKEN = "TMDB_Token"
        const val PARAM_MOVIEDB_TOKEN = "?api_key=$MOVIEDB_TOKEN"

        const val MOVIEDB_IMAGE = "https://image.tmdb.org/t/p/w500"
        const val MOVIEDB_LANGUAGE = "&language=pt-BR"

        const val GET_POPULAR_MOVIES_API = "movie/popular"
        const val GET_MOVIE_DETAILS_API = "/movie/"
    }
}