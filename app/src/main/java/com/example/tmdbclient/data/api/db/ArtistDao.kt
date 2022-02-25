package com.example.tmdbclient.data.api.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdbclient.data.api.model.artist.Artist
import com.example.tmdbclient.data.api.model.tvshow.TvShow

@Dao
interface ArtistDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun saveArtist(artists: List<Artist>)

        @Query("DELETE FROM popular_artists")
        suspend fun deleteAllArtists()

        @Query("SELECT * FROM popular_artists")
        suspend fun getArtists(): List<Artist>

}