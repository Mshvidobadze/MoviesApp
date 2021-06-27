package com.example.moviesapp.popularTVSeries

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.model.api.TMDBInterface
import com.example.moviesapp.model.api.TMDBObject
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewModel: PopularViewModel
    private lateinit var airTodayViewModel: AirTodayViewModel
    private lateinit var airViewModel: AirViewModel
    private lateinit var topRatedViewModel: TopRatedViewModel
    lateinit var tvSeriesRepository: PopularDetailsRepository
    private val listAdapter = PopularListAdapter(this)
    private var filterType: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val seriesRecyclerView = tvSeriesRecyclerView
        val gridLayoutManager = GridLayoutManager(this, 2)
        val tmdbInterface: TMDBInterface = TMDBObject.getTMDBObject()
        tvSeriesRepository = PopularDetailsRepository(tmdbInterface)

        seriesRecyclerView.layoutManager = gridLayoutManager
        seriesRecyclerView.setHasFixedSize(true)
        seriesRecyclerView.adapter = listAdapter

        val popularButton: Button = findViewById(R.id.btnPopular)
        popularButton.setOnClickListener(this)
        val airTodayButton: Button = findViewById(R.id.btnAirToday)
        airTodayButton.setOnClickListener(this)
        val airButton: Button = findViewById(R.id.btnAir)
        airButton.setOnClickListener(this)
        val topRatedButton: Button = findViewById(R.id.btnTopRated)
        topRatedButton.setOnClickListener(this)

        popularButton.callOnClick()



    }

    private fun getFilteredList(type: String){
        //Get viewModel

        if(type == "popular"){
            viewModel = getViewModel(type)

            viewModel.mPopularList.observe(this, Observer {
                listAdapter.submitList(it)

            })

            viewModel.mStateStatus.observe(this, Observer {
                progress_bar_popular.visibility = if (viewModel.listIsEmpty()) View.VISIBLE else View.GONE

            })
        }
        if(type == "airtoday"){
            airTodayViewModel = getAirTodayViewModel(type)

            airTodayViewModel.mAirTodayList.observe(this, Observer {
                listAdapter.submitList(it)

            })

            airTodayViewModel.mStateStatus.observe(this, Observer {
                progress_bar_popular.visibility = if (viewModel.listIsEmpty()) View.VISIBLE else View.GONE

            })
        }
        if(type == "air"){
            airViewModel = getAirViewModel(type)

            airViewModel.mAirList.observe(this, Observer {
                listAdapter.submitList(it)

            })

            airViewModel.mStateStatus.observe(this, Observer {
                progress_bar_popular.visibility = if (viewModel.listIsEmpty()) View.VISIBLE else View.GONE

            })
        }
        if(type == "toprated"){
            topRatedViewModel = getTopRatedViewModel(type)

            topRatedViewModel.mTopRatedList.observe(this, Observer {
                listAdapter.submitList(it)

            })

            topRatedViewModel.mTopRatedList.observe(this, Observer {
                progress_bar_popular.visibility = if (viewModel.listIsEmpty()) View.VISIBLE else View.GONE

            })
        }



    }

    private fun getViewModel(type: String): PopularViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {

                @Suppress("UNCHECKED_CAST")
                return PopularViewModel(type, tvSeriesRepository) as T

            }
        })[PopularViewModel::class.java]
    }

    private fun getAirTodayViewModel(type: String): AirTodayViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return AirTodayViewModel(type, tvSeriesRepository) as T

            }
        })[AirTodayViewModel::class.java]
    }
    private fun getAirViewModel(type: String): AirViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {

                @Suppress("UNCHECKED_CAST")
                return AirViewModel(type, tvSeriesRepository) as T

            }
        })[AirViewModel::class.java]
    }
    private fun getTopRatedViewModel(type: String): TopRatedViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return TopRatedViewModel(type, tvSeriesRepository) as T

            }
        })[TopRatedViewModel::class.java]
    }

    override fun onClick(v: View?) {

        if(v == btnPopular){
            filterType = "popular"
            getFilteredList(filterType)
        }
        if(v == btnAirToday){
            filterType = "airtoday"
            getFilteredList(filterType)
        }
        if(v == btnAir){
            filterType = "air"
            getFilteredList(filterType)
        }
        if(v == btnTopRated){
            filterType = "toprated"
            getFilteredList(filterType)
        }


    }
}