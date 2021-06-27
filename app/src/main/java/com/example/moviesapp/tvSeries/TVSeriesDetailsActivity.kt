package com.example.moviesapp.tvSeries

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.model.api.POSTER_URL
import com.example.moviesapp.model.api.TMDBInterface
import com.example.moviesapp.model.api.TMDBObject
import com.example.moviesapp.similarTVSeries.SimilarDetailsRepository
import com.example.moviesapp.similarTVSeries.SimilarListAdapter
import com.example.moviesapp.similarTVSeries.SimilarViewModel
import kotlinx.android.synthetic.main.activity_tvseries_details_page.*


class TVSeriesDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: TVSeriesViewModel
    private lateinit var tvSeriesRepository: TVSeriesDetailsRepository

    private lateinit var similarViewModel: SimilarViewModel
    private lateinit var similarTVSeriesRepository: SimilarDetailsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tvseries_details_page)

        val tvSeriesId: Int = intent.getIntExtra("tvSeriesId", 0)
        val posterImg: ImageView = findViewById(R.id.tvPoster)
        val titleTxt: TextView = findViewById(R.id.tvTitle)
        val firstAirDate: TextView = findViewById(R.id.tvFirstAirDate)
        val voteAverageTxt: TextView = findViewById(R.id.tvVoteAverage)
        val overviewTxt: TextView = findViewById(R.id.tvOverview)

        val seriesRecyclerView = similarTVSeriesRecyclerView
        val listAdapter = SimilarListAdapter(this)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val tmdbInterface: TMDBInterface = TMDBObject.getTMDBObject()
        tvSeriesRepository = TVSeriesDetailsRepository(tmdbInterface)
        similarTVSeriesRepository = SimilarDetailsRepository(tmdbInterface)

        //Get viewModel
        viewModel = getViewModel(tvSeriesId)

        //Display changes
        viewModel.mTVSeriesDetails.observe(this, Observer {
//            Toast.makeText(applicationContext,it.name,Toast.LENGTH_LONG).show()

            titleTxt.text = it.name
            firstAirDate.text = it.firstAirDate
            voteAverageTxt.text = it.voteAverage.toString()
            overviewTxt.text = it.overview

            val tvPosterUrl = POSTER_URL + it.posterPath
            Glide.with(this)
                    .load(tvPosterUrl)
                    .into(posterImg)

        })

        //Get SimilarViewModel
        similarViewModel = getSimilarViewModel(tvSeriesId)

        seriesRecyclerView.layoutManager = linearLayoutManager
        seriesRecyclerView.setHasFixedSize(true)
        val dividerItemDecoration = DividerItemDecoration(seriesRecyclerView.context,
                linearLayoutManager.orientation)
        seriesRecyclerView.addItemDecoration(dividerItemDecoration)
        seriesRecyclerView.adapter = listAdapter

//        val marginLayoutParams = MarginLayoutParams(seriesRecyclerView.layoutParams)
//        marginLayoutParams.setMargins(0, 0, 5, 0)
//        seriesRecyclerView.layoutParams = marginLayoutParams

        similarViewModel.mSimilarList.observe(this, Observer {
            listAdapter.submitList(it)
//            Toast.makeText(applicationContext,it.isEmpty().toString(),Toast.LENGTH_LONG).show()

        })

    }

    private fun getViewModel(tvSeriesId: Int): TVSeriesViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return TVSeriesViewModel(tvSeriesId, tvSeriesRepository) as T

            }
        })[TVSeriesViewModel::class.java]
    }

    private fun getSimilarViewModel(tvSeriesId: Int): SimilarViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return SimilarViewModel(tvSeriesId, similarTVSeriesRepository) as T
            }
        })[SimilarViewModel::class.java]
    }

}