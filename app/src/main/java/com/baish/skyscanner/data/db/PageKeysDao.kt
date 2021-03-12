package com.baish.skyscanner.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.baish.skyscanner.data.model.nasa.mars.PageKeys


@Dao
interface PageKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<PageKeys>)

    @Query("SELECT * FROM pagekeys WHERE id = :id")
    suspend fun getKeyId(id: Int?): PageKeys?

    @Query("DELETE FROM pagekeys")
    suspend fun deleteAll()
}
