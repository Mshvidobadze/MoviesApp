package com.example.moviesapp.popularTVSeries

import android.content.Context
import android.content.Intent
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.model.api.POSTER_URL
import com.example.moviesapp.model.repository.RepositoryState
import com.example.moviesapp.model.values.PopularTVSeriesDetails
import com.example.moviesapp.tvSeries.TVSeriesDetailsActivity
import kotlinx.android.synthetic.main.tvseries_list_item.view.*

class PopularListAdapter(private val context: Context) : PagedListAdapter<PopularTVSeriesDetails, RecyclerView.ViewHolder>(PopularDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.tvseries_list_item, parent, false)
        return PopularItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PopularItemViewHolder).bind(getItem(position),context)
    }



    class PopularDiffCallback : DiffUtil.ItemCallback<PopularTVSeriesDetails>(){
        override fun areItemsTheSame(oldItem: PopularTVSeriesDetails, newItem: PopularTVSeriesDetails): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PopularTVSeriesDetails, newItem: PopularTVSeriesDetails): Boolean {
            return oldItem == newItem
        }

    }

    class PopularItemViewHolder (view: View) : RecyclerView.ViewHolder(view){
        fun bind(movie: PopularTVSeriesDetails?,context: Context) {
            itemView.txtName.text = movie?.name
            itemView.txtRating.text =  movie?.voteAverage.toString()

            val moviePosterURL = POSTER_URL + movie?.posterPath
            Glide.with(itemView.context)
                    .load(moviePosterURL)
                    .into(itemView.imgPoster);

            itemView.setOnClickListener{
                val intent = Intent(context, TVSeriesDetailsActivity::class.java)
                intent.putExtra("tvSeriesId", movie?.id)
                context.startActivity(intent)
            }

        }

    }



}