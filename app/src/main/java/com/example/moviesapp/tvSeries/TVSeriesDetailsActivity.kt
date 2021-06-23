package com.example.moviesapp.tvSeries

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.model.api.POSTER_URL
import com.example.moviesapp.model.api.TMDBInterface
import com.example.moviesapp.model.api.TMDBObject
import com.example.moviesapp.model.values.TVSeriesDetails
import kotlinx.android.synthetic.main.activity_tvseries_details_page.*
import java.text.NumberFormat
import java.util.*

class TVSeriesDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: TVSeriesViewModel
    private lateinit var tvSeriesRepository: TVSeriesDetailsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tvseries_details_page)

        val tvSeriesId: Int = intent.getIntExtra("tvSeriesId",0)
//        val posterImg: ImageView = findViewById(R.id.tvPoster)
//        val titleTxt: TextView = findViewById(R.id.tvTitle)
//        val taglineTxt: TextView = findViewById(R.id.tvTagline)
//        val releaseDateTxt: TextView = findViewById(R.id.tvReleaseDate)
//        val voteAverageTxt: TextView = findViewById(R.id.tvVoteAverage)
//        val runtimeTxt: TextView = findViewById(R.id.tvRuntime)
//        val overviewTxt: TextView = findViewById(R.id.tvOverview)

        val tmdbInterface: TMDBInterface = TMDBObject.getTMDBObject()
        tvSeriesRepository = TVSeriesDetailsRepository(tmdbInterface)

        //Get viewModel
        viewModel = getViewModel(tvSeriesId)



//        Display changes
        viewModel.mTVSeriesDetails.observe(this, {

            displayData(it)

        })

    }

    fun displayData( it: TVSeriesDetails){

        tvTitle.text = it.title
        tvTagline.text = it.tagline
        tvReleaseDate.text = it.releaseDate
        tvVoteAverage.text = it.voteAverage.toString()
        tvRuntime.text = it.runtime.toString()
        tvOverview.text = it.overview

        val tvPosterUrl = POSTER_URL + it.posterPath
        Glide.with(this)
            .load(tvPosterUrl)
            .into(tvPoster);


    }

    private fun getViewModel(tvSeriesId:Int): TVSeriesViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return TVSeriesViewModel(tvSeriesId, tvSeriesRepository) as T
            }
        })[TVSeriesViewModel::class.java]
    }

}