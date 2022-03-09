package com.example.tmdbclient.domain.usecase

import com.example.tmdbclient.data.api.model.artist.Artist
import com.example.tmdbclient.domain.repository.ArtistRepository

class GetArtistsUseCase(private val artistRepository: ArtistRepository) {
    suspend fun execute() :List<Artist>? = artistRepository.getArtists()
}