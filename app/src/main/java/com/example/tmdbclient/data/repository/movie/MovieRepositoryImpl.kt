package com.example.tmdbclient.data.repository.movie

import android.util.Log
import com.example.tmdbclient.data.api.model.movie.Movie
import com.example.tmdbclient.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val moviesRemoteDatasource: MoviesRemoteDatasource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
) : MovieRepository {
    override suspend fun getMovies(): List<Movie>? {
       return getMoviesFromCache()
    }

    override suspend fun updateMovies(): List<Movie>? {
       val newListOfMovies = getMoviesFromAPI()
        movieLocalDataSource.clearAll()
        movieLocalDataSource.saveMoviesToDB(newListOfMovies)
        movieCacheDataSource.saveMoviesToCache(newListOfMovies)
        return newListOfMovies
    }

    suspend fun getMoviesFromAPI():List<Movie>{
        lateinit var movieList: List<Movie>
        try {
            val response = moviesRemoteDatasource.getMovies()
            val body = response.body()
            if(body!=null){
                movieList = body.movies
            }

        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }

        return movieList
    }

    suspend fun getMoviesFromDB():List<Movie>{
        lateinit var movieList: List<Movie>
        try {
         movieList = movieLocalDataSource.getMoviesFromDB()
        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        if(movieList.size>0){//db不為空
            return movieList
        }else{//db為空 直接從api抓 抓完save to db
            movieList=getMoviesFromAPI()
            movieLocalDataSource.saveMoviesToDB(movieList)
        }

        return movieList

        }

    suspend fun getMoviesFromCache():List<Movie>{
        lateinit var movieList: List<Movie>
        try {
            movieList = movieCacheDataSource.getMoviesFromCache()
        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        if(movieList.size>0){//
            return movieList
        }else{
            movieList=getMoviesFromDB()
            movieCacheDataSource.saveMoviesToCache(movieList)
        }

        return movieList

    }

}