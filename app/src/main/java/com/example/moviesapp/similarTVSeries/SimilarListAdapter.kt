package com.example.moviesapp.similarTVSeries

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginEnd
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.model.api.POSTER_URL
import com.example.moviesapp.model.values.SimilarTVSeriesDetails
import com.example.moviesapp.tvSeries.TVSeriesDetailsActivity
import kotlinx.android.synthetic.main.tvseries_list_item.view.*

class SimilarListAdapter (private val context: Context) : PagedListAdapter<SimilarTVSeriesDetails, RecyclerView.ViewHolder>(SimilarDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.tvseries_list_item, parent, false)
        view.layoutParams = ViewGroup.LayoutParams((parent.width * 0.5).toInt(),ViewGroup.LayoutParams.MATCH_PARENT)

        return SimilarItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SimilarItemViewHolder).bind(getItem(position),context)
    }

    class SimilarDiffCallback : DiffUtil.ItemCallback<SimilarTVSeriesDetails>(){
        override fun areItemsTheSame(oldItem: SimilarTVSeriesDetails, newItem: SimilarTVSeriesDetails): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SimilarTVSeriesDetails, newItem: SimilarTVSeriesDetails): Boolean {
            return oldItem == newItem
        }

    }

    class SimilarItemViewHolder (view: View) : RecyclerView.ViewHolder(view){
        fun bind(movie: SimilarTVSeriesDetails?, context: Context) {
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