package com.example.tmdbclient.data.repository.artist.datasource

import com.example.tmdbclient.data.api.model.artist.ArtistList
import retrofit2.Response

interface ArtistsRemoteDatasource {
    suspend fun getArtists(): Response<ArtistList>
}