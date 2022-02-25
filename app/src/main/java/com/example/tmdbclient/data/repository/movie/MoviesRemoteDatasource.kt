package com.example.tmdbclient.data.repository.movie

import com.example.tmdbclient.data.api.model.movie.MovieList
import retrofit2.Response

interface MoviesRemoteDatasource {
    suspend fun getMovies(): Response<MovieList>
}