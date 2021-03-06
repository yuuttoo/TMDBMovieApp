package com.example.tmdbclient.data.repository.movie.datasource

import com.example.tmdbclient.data.api.model.movie.Movie

interface MovieCacheDataSource {
    suspend fun getMoviesFromCache():List<Movie>
    suspend fun saveMoviesToCache(movies:List<Movie>)
}