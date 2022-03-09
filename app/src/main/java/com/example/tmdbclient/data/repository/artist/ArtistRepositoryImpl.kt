package com.example.tmdbclient.data.repository.artist

import android.util.Log
import com.example.tmdbclient.data.api.model.artist.Artist
import com.example.tmdbclient.data.api.model.artist.ArtistList
import com.example.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistsRemoteDatasource
import com.example.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MoviesRemoteDatasource
import com.example.tmdbclient.domain.repository.ArtistRepository
import kotlin.Exception

class ArtistRepositoryImpl(
    private val artistsRemoteDatasource: ArtistsRemoteDatasource,
    private val artistLocalDataSource: ArtistLocalDataSource,
    private val artistCacheDataSource: ArtistCacheDataSource
) : ArtistRepository {
    override suspend fun getArtists(): List<Artist>? {
        return getArtistsFromCache()
    }

    override suspend fun updateArtists(): List<Artist>? {
        val newListOfArtist = getArtistsFromAPI()
        artistLocalDataSource.clearAll()
        artistLocalDataSource.saveArtistsToDB(newListOfArtist)
        artistCacheDataSource.saveArtistsToCache(newListOfArtist)
        return newListOfArtist
    }

    suspend fun getArtistsFromAPI():List<Artist>{
        lateinit var artistList:  List<Artist>
        try {
            val response = artistsRemoteDatasource.getArtists()
            val body = response.body()
            if(body!=null){
                artistList = body.artists
            }

        } catch (exception: Exception) {
            Log.i("ArtistRepositoryImpl", exception.message.toString())

        }
        return artistList
    }

    suspend fun getArtistsFromDB():List<Artist>{
        lateinit var artistList: List<Artist>
        try {
            artistList = artistLocalDataSource.getArtistsFromDB()
        } catch (exception: Exception) {
            Log.i("ArtistRepositoryImpl", exception.message.toString())
        }
        if(artistList.size>0){
            return artistList
        } else{
            artistList=getArtistsFromAPI()
            artistLocalDataSource.saveArtistsToDB(artistList)
        }

        return artistList
    }

    suspend fun getArtistsFromCache():List<Artist>{
        lateinit var artistList: List<Artist>
        try {
            artistList = artistCacheDataSource.getArtistsFromCache()
        } catch (exception: Exception) {
            Log.i("ArtistRepositoryImpl", exception.message.toString())
        }
        if(artistList.size>0){
            return artistList
        }else {
            artistList=getArtistsFromDB()
            artistCacheDataSource.saveArtistsToCache(artistList)
        }

        return artistList
    }


}