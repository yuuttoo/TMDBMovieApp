package com.example.tmdbclient.presentation.artist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbclient.R
import com.example.tmdbclient.data.api.model.artist.Artist
import com.example.tmdbclient.data.api.model.movie.Movie
import com.example.tmdbclient.databinding.ListItemBinding

class ArtistAdapter(): RecyclerView.Adapter<MyViewHolder>(){
    private val artistList = ArrayList<Artist>()

    fun setList(artists:List<Artist>){
        artistList.clear()
        artistList.addAll(artists)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ListItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.list_item,
            parent,
            false

        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(artistList[position])
    }

    override fun getItemCount(): Int {
        return artistList.size
    }


}

class MyViewHolder(val binding: ListItemBinding):
    RecyclerView.ViewHolder(binding.root){

        fun bind(artist: Artist){
            binding.titleTextView.text = artist.name
            binding.descriptionTextView.text = "Popularity Level : " + artist.popularity.toString()
            val posterURL = "https://image.tmdb.org/t/p/w500"+artist.profilePath//api image
            Glide.with(binding.imageView.context)
                .load(posterURL)
                .into(binding.imageView)
        }

    }









