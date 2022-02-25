package com.example.tmdbclient.domain.repository

import com.example.tmdbclient.data.api.model.artist.Artist

interface ArtistRepository {
    suspend fun getArtists(): List<Artist>?
    suspend fun updateArtists(): List<Artist>?
}