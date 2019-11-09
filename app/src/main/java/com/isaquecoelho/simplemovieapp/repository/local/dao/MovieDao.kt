package com.isaquecoelho.simplemovieapp.repository.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.isaquecoelho.simplemovieapp.model.MovieItem

@Dao
interface MovieDao {

    @Query("SELECT * from movie")
    fun getMovieList(): LiveData<MutableList<MovieItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieList: List<MovieItem>?)

    @Query("DELETE FROM `movie`")
    fun deleteAll()
/*
    @Query("SELECT * from movie WHERE id = :movieId")
    fun getMovieById(movieId: Int): Maybe<Movie>

    @Query("DELETE FROM movie")
    fun delete()*/
}