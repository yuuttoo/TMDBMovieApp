package com.example.tmdbclient.data.api.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdbclient.data.api.model.tvshow.TvShow

@Dao
interface TvShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTvShows(tvshows: List<TvShow>)

    @Query("DELETE FROM popular_tvshows")
    suspend fun deleteAllTvShows()

    @Query("SELECT * FROM popular_movies")
    suspend fun getTvShows():List<TvShow>
}