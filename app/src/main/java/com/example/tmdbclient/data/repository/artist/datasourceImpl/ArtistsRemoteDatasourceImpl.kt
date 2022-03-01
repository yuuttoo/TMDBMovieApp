package com.example.tmdbclient.data.repository.artist.datasourceImpl

import com.example.tmdbclient.data.api.TMDBService
import com.example.tmdbclient.data.api.model.artist.ArtistList
import com.example.tmdbclient.data.repository.artist.datasource.ArtistsRemoteDatasource
import retrofit2.Response

class ArtistsRemoteDatasourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String
): ArtistsRemoteDatasource {
    override suspend fun getArtists(): Response<ArtistList> = tmdbService.getPopularArtists(apiKey)
}