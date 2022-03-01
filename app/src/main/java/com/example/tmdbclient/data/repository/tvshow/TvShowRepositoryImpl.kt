package com.example.tmdbclient.data.repository.tvshow

import android.util.Log
import com.example.tmdbclient.data.api.model.tvshow.TvShow
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowsRemoteDataSource
import com.example.tmdbclient.domain.repository.TvShowRepository
import kotlin.Exception

class TvShowRepositoryImpl(
    private val tvShowsRemoteDataSource: TvShowsRemoteDataSource,
    private val tvShowLocalDataSource: TvShowLocalDataSource,
    private val tvShowCacheDataSource: TvShowCacheDataSource
) : TvShowRepository {
    override suspend fun getTvShows(): List<TvShow>? {
        return getTvShowsFromCache()
    }

    override suspend fun updateTvShows(): List<TvShow>? {
        val newListOfTvShows = getTvShowsFromAPI()
        tvShowLocalDataSource.clearAll()
        tvShowLocalDataSource.saveTvShowsToDB(newListOfTvShows)
        tvShowCacheDataSource.saveTvShowsToChche(newListOfTvShows)
        return newListOfTvShows
    }

    suspend fun getTvShowsFromAPI(): List<TvShow>{
        lateinit var tvshowList: List<TvShow>
        try {
            val response  = tvShowsRemoteDataSource.getTvShows()
            val body = response.body()
            if(body!=null){
                tvshowList = body.tvShows
            }
        } catch (exception: Exception) {
            Log.i("TvShowRepositoryImpl", exception.message.toString())
        }
        return tvshowList

    }

    suspend fun getTvShowsFromDB(): List<TvShow>{
        lateinit var tvShowList: List<TvShow>
        try {
            tvShowList = tvShowLocalDataSource.getTvShowsFromDB()
        } catch (exception: Exception) {
            Log.i("TvShowRepositoryImpl", exception.message.toString())
        }
        if(tvShowList.size>0){
            return tvShowList
        } else {
            tvShowList=getTvShowsFromAPI()
            tvShowLocalDataSource.saveTvShowsToDB(tvShowList)
        }
        return tvShowList
    }

    suspend fun getTvShowsFromCache(): List<TvShow>{
        lateinit var tvShowList: List<TvShow>
        try {
            tvShowList = tvShowCacheDataSource.getTvShowsFromCache()
        } catch (exception: Exception) {
            Log.i("TvShowRepositoryImpl", exception.message.toString())
        }
        if(tvShowList.size>0){
            return tvShowList
        } else {
            tvShowList=getTvShowsFromDB()
            tvShowCacheDataSource.saveTvShowsToChche(tvShowList)
        }
        return tvShowList
    }

}