package com.example.tmdbclient.data.api.model.tvshow


import com.example.tmdbclient.data.api.model.tvshow.TvShow
import com.google.gson.annotations.SerializedName

data class TvShowList(

    @SerializedName("results")
    val tvShows: List<TvShow>,

    )