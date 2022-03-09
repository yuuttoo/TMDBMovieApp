package com.example.tmdbclient.data.repository.artist.datasource

import com.example.tmdbclient.data.api.model.artist.Artist

interface ArtistLocalDataSource {
    suspend fun getArtistsFromDB():List<Artist>
    suspend fun saveArtistsToDB(artist: List<Artist>)
    suspend fun clearAll()

}