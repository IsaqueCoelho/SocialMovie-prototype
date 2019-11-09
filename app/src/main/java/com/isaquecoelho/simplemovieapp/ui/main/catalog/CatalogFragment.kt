package com.isaquecoelho.simplemovieapp.ui.main.catalog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.isaquecoelho.simplemovieapp.R
import com.isaquecoelho.simplemovieapp.databinding.CatalogFragmentBinding
import com.isaquecoelho.simplemovieapp.viewmodel.CatalogViewModel
import kotlinx.android.synthetic.main.activity_signin.*

class CatalogFragment : Fragment() {

    companion object {
        fun newInstance() = CatalogFragment()
    }

    private val LOG_TAG = CatalogFragment::class.java.simpleName

    private lateinit var mViewModel: CatalogViewModel
    private lateinit var mMovieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mViewModel = ViewModelProviders.of(this).get(CatalogViewModel::class.java)

        val mBiding =
            DataBindingUtil.inflate<CatalogFragmentBinding>(inflater, R.layout.catalog_fragment, container, false)

        mBiding.viewmodel = mViewModel
        mMovieAdapter = MovieAdapter(mViewModel.getMovieList()?.value)

        mBiding.recyclerviewCatalogList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mBiding.recyclerviewCatalogList.setHasFixedSize(true)
        mBiding.recyclerviewCatalogList.adapter = mMovieAdapter

        mBiding.setLifecycleOwner { lifecycle }

        listeningDatabase()

        return mBiding.root
    }

    private fun listeningDatabase() {
        mViewModel.getMovieList()?.observe(this, Observer {
            mMovieAdapter.updateMovieList(it)
            Log.e(LOG_TAG, "movieList: ${it.size}")
            Log.e(LOG_TAG, "movie title: ${it[0].title}, vote averange: ${it[0].voteAverage}")
        })
    }

}
