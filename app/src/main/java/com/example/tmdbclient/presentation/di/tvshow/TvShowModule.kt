package com.example.tmdbclient.presentation.di.tvshow

import com.example.tmdbclient.domain.usecase.GetArtistsUseCase
import com.example.tmdbclient.domain.usecase.GetTvShowsUseCase
import com.example.tmdbclient.domain.usecase.UpdateArtistsUseCase
import com.example.tmdbclient.domain.usecase.UpdateTvShowsUseCase
import com.example.tmdbclient.presentation.artist.ArtistViewModelFactory
import com.example.tmdbclient.presentation.di.movie.MovieScope
import com.example.tmdbclient.presentation.tv.TvShowViewModelFactory
import dagger.Module
import dagger.Provides


@Module
class TvShowModule {
    @MovieScope
    @Provides
    fun provideTvShowViewModelFactory(
        getTvShowsUseCase: GetTvShowsUseCase,
        updateTvShowsUseCase: UpdateTvShowsUseCase
    ): TvShowViewModelFactory {
        return TvShowViewModelFactory(
            getTvShowsUseCase,
            updateTvShowsUseCase
        )
    }
}