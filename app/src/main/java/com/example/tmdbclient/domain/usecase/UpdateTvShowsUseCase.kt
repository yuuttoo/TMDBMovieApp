package com.example.tmdbclient.domain.usecase

import com.example.tmdbclient.data.api.model.tvshow.TvShow
import com.example.tmdbclient.domain.repository.TvShowRepository

class UpdateTvShowsUseCase(private val tvShowRepository: TvShowRepository) {
    suspend fun execute(): List<TvShow>? = tvShowRepository.updateTvShows()
}