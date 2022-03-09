package com.example.tmdbclient.data.repository.tvshow.datasourceImpl

import com.example.tmdbclient.data.api.TMDBService
import com.example.tmdbclient.data.api.db.TvShowDao
import com.example.tmdbclient.data.api.model.tvshow.TvShow
import com.example.tmdbclient.data.api.model.tvshow.TvShowList
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowsRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class TvShowLocalDataSourceImpl(private val tvShowDao: TvShowDao): TvShowLocalDataSource {
    override suspend fun getTvShowsFromDB(): List<TvShow> {
        return tvShowDao.getTvShows()
        }

    override suspend fun saveTvShowsToDB(tvShows: List<TvShow>) {
       CoroutineScope(Dispatchers.IO).launch {
           tvShowDao.saveTvShows(tvShows)
       }
    }

    override suspend fun clearAll() {
       CoroutineScope(Dispatchers.IO).launch {
           tvShowDao.deleteAllTvShows()
       }
    }

}