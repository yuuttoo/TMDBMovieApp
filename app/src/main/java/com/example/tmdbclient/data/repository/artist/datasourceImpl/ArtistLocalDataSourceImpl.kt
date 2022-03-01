package com.example.tmdbclient.data.repository.artist.datasourceImpl

import com.example.tmdbclient.data.api.db.ArtistDao
import com.example.tmdbclient.data.api.model.artist.Artist
import com.example.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistLocalDataSourceImpl(private val artistDao: ArtistDao): ArtistLocalDataSource {
    override suspend fun getArtistsFromDB(): List<Artist> {
       return artistDao.getArtists()
    }

    override suspend fun saveArtistsToDB(artist: List<Artist>) {
        CoroutineScope(Dispatchers.IO).launch {
             artistDao.saveArtist(artist)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
             artistDao.deleteAllArtists()
        }
    }

}