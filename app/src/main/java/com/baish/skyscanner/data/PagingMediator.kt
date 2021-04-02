package com.baish.skyscanner.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.baish.skyscanner.data.db.AppDataBase
import com.baish.skyscanner.data.model.nasa.mars.PageKeys
import com.baish.skyscanner.data.model.nasa.mars.Photos
import com.baish.skyscanner.data.remote.NasaService
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException

const val START_PAGE = 1

@ExperimentalPagingApi
class PagingMediator(
    private val service: NasaService,
    private val db: AppDataBase,
) : RemoteMediator<Int, Photos>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Photos>): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeysToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: START_PAGE
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyforFirstItem(state)
                remoteKeys?.prevKey ?: return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                remoteKeys.prevKey
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeysForLastItem(state)
                remoteKeys?.nextKey ?: 2
            }
        }

        try {
            Log.d("gdfgd", page.toString())
            val apiResponse = service.getMars(sol = state.config.pageSize,
                page = page,
                api_key = "EnKUTj7VDqVE0Cnu63SCYny69JzzTllJdhzVCmZb")
            val endofPaginationReached = apiResponse.photos.isEmpty()

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.getContentDao().deleteALL()
                    db.getPagigngKeysDao().deleteAll()
                }

                val prevKey = if (page == START_PAGE) null else page - 1
                val nextKey = if (endofPaginationReached) null else page + 1

                val keys = apiResponse.photos.map {
                    PageKeys(id = it.id, prevKey = prevKey,nextKey = nextKey)
                }

                db.getPagigngKeysDao().insertAll(keys)
                db.getContentDao().insert(apiResponse.photos)

            }

            return MediatorResult.Success(endOfPaginationReached = endofPaginationReached)

        } catch (e : IOException){
            return MediatorResult.Error(e)
        } catch (e: HttpException){
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeysToCurrentPosition(
        state: PagingState<Int, Photos>,
    ): PageKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id.let { repoId ->
                db.getPagigngKeysDao().getKeyId(repoId)
            }
        }
    }

    private suspend fun getRemoteKeyforFirstItem(state: PagingState<Int, Photos>): PageKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { repo ->
                db.getPagigngKeysDao().getKeyId(repo.id)
            }
    }

    private suspend fun getRemoteKeysForLastItem(state: PagingState<Int, Photos>): PageKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { repo ->
                db.getPagigngKeysDao().getKeyId(repo.id)
            }
    }
}
