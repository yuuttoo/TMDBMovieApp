package com.example.tmdbclient.data.repository.tvshow.datasource

import com.example.tmdbclient.data.api.model.tvshow.TvShow

interface TvShowCacheDataSource {
    suspend fun getTvShowsFromCache():List<TvShow>
    suspend fun saveTvShowsToChche(tvshows: List<TvShow>)
}