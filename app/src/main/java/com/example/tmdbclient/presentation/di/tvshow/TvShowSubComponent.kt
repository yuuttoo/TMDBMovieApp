package com.example.tmdbclient.presentation.di.tvshow

import com.example.tmdbclient.presentation.artist.ArtistActivity
import com.example.tmdbclient.presentation.di.movie.MovieScope
import com.example.tmdbclient.presentation.tv.TvShowActivity
import dagger.Subcomponent


@TvShowScope
@Subcomponent(modules = [TvShowModule::class])
interface TvShowSubComponent {
    //將要inject的目標作為參數
    fun inject(tvShowActivity: TvShowActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create():TvShowSubComponent
    }
}