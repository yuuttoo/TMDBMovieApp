package com.example.tmdbclient.data.repository.movie

import com.example.tmdbclient.data.api.model.movie.Movie


//Singleton 使用者有需要再手動抓一次
class MovieCacheDataSourceImpl: MovieCacheDataSource {
    private var movieList = ArrayList<Movie>()

    override suspend fun getMoviesFromCache(): List<Movie> {
        return movieList
    }

    override suspend fun saveMoviesToCache(movies: List<Movie>) {
        movieList.clear()
        movieList = ArrayList(movies)
    }
}