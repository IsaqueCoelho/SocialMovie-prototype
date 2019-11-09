package com.isaquecoelho.simplemovieapp.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
data class Movie(@SerializedName("page") val page : Int,
                 @SerializedName("total_results") val total_results : Int,
                 @SerializedName("total_pages") val total_pages : Int,
                 @SerializedName("results") val movieList : List<MovieItem>)