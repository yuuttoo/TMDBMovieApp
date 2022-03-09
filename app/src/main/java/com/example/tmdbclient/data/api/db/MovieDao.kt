package com.example.tmdbclient.data.api.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdbclient.data.api.model.movie.Movie
import com.example.tmdbclient.data.api.model.movie.MovieList


@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)//蓋過舊資料
    suspend fun saveMovies(movies : List<Movie>)

    @Query("DELETE FROM popular_movies")//後面為建立Entity時的column名稱
    suspend fun deleteAllMovies()

    @Query("SELECT * FROM popular_movies")//*為全部
    suspend fun getMovies():List<Movie>

}