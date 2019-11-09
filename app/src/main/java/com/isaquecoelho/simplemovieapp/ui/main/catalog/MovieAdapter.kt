package com.isaquecoelho.simplemovieapp.ui.main.catalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.isaquecoelho.simplemovieapp.R
import com.isaquecoelho.simplemovieapp.model.MovieItem
import com.isaquecoelho.simplemovieapp.util.ConstantUtils
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private var mMovieList: MutableList<MovieItem>?) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val movieView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder( movieView )
    }

    override fun getItemCount(): Int {
        return if( mMovieList != null ) mMovieList!!.size else 0
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = mMovieList?.get(position)
        holder.bind(movie)
    }

    fun updateMovieList(movieList: MutableList<MovieItem>){
        this.mMovieList = movieList
        notifyDataSetChanged()
    }

    class MovieViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){

        fun bind(movie: MovieItem?) {

            Glide.with(itemView.context)
                .load(ConstantUtils.MOVIEDB_IMAGE + movie?.posterPath)
                .into(itemView.imageview_movie_poster)

            itemView.textview_movie_title.text = movie?.title
            itemView.textview_movie_vote_average.text = movie?.voteAverage.toString()
        }
    }
}