package com.example.tmdbclient.data.api.model.movie


import com.example.tmdbclient.data.api.model.movie.Movie
import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val movies: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)