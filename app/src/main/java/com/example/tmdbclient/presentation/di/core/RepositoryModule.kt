package com.example.tmdbclient.presentation.di.core

import com.example.tmdbclient.data.api.model.artist.Artist
import com.example.tmdbclient.data.repository.artist.ArtistRepositoryImpl
import com.example.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistsRemoteDatasource
import com.example.tmdbclient.data.repository.movie.MovieRepositoryImpl
import com.example.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MoviesRemoteDatasource
import com.example.tmdbclient.data.repository.tvshow.TvShowRepositoryImpl
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowsRemoteDataSource
import com.example.tmdbclient.domain.repository.ArtistRepository
import com.example.tmdbclient.domain.repository.MovieRepository
import com.example.tmdbclient.domain.repository.TvShowRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        moviesRemoteDatasource: MoviesRemoteDatasource,
        movieLocalDataSource: MovieLocalDataSource,
        movieCacheDataSource: MovieCacheDataSource
    ) : MovieRepository {
        return MovieRepositoryImpl(
            moviesRemoteDatasource,
            movieLocalDataSource,
            movieCacheDataSource
        )

    }

    @Provides
    @Singleton
    fun provideTvShowRepository(
        tvShowsRemoteDataSource: TvShowsRemoteDataSource,
        tvShowLocalDataSource: TvShowLocalDataSource,
        tvShowCacheDataSource: TvShowCacheDataSource
    ) : TvShowRepository {
        return TvShowRepositoryImpl(
            tvShowsRemoteDataSource,
            tvShowLocalDataSource,
            tvShowCacheDataSource
        )

    }


    @Provides
    @Singleton
    fun provideArtistRepository(
        artistsRemoteDatasource: ArtistsRemoteDatasource,
        artistLocalDataSource: ArtistLocalDataSource,
        artistCacheDataSource: ArtistCacheDataSource
    ) : ArtistRepository {
        return ArtistRepositoryImpl(
            artistsRemoteDatasource,
            artistLocalDataSource,
            artistCacheDataSource
        )

    }
}