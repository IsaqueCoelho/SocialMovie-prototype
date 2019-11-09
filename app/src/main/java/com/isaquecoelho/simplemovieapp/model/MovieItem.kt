package com.isaquecoelho.simplemovieapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
data class MovieItem (
    @SerializedName("id") @PrimaryKey(autoGenerate = false) val id: Int,
    @SerializedName("title") @ColumnInfo(name = "title") val title: String,
    @SerializedName("release_date") @ColumnInfo(name = "release_date") val releaseDate: String,
    @SerializedName("vote_average") @ColumnInfo(name = "vote_average") val voteAverage: Float,
    @SerializedName("poster_path") @ColumnInfo(name = "poster_path") val posterPath: String,
    @SerializedName("backdrop_path") @ColumnInfo(name = "backdrop_path") val bannerPath: String,
    @SerializedName("overview") @ColumnInfo(name = "overview") val overview: String
)