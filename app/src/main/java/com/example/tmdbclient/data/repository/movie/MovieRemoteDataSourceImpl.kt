package com.example.tmdbclient.data.repository.movie

import com.example.tmdbclient.data.api.TMDBService
import com.example.tmdbclient.data.api.model.movie.MovieList
import retrofit2.Response

class MovieRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String
    ): MoviesRemoteDatasource {//繼承TMDBService的method
    override suspend fun getMovies(): Response<MovieList> = tmdbService.getPopularMovies(apiKey)
}