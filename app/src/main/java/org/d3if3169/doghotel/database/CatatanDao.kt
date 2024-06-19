package org.d3if3169.doghotel.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if3169.doghotel.model.Catatan

@Dao
interface CatatanDao {

    @Insert
    suspend fun insert(catatan: Catatan)
    @Update
    suspend fun update(catatan: Catatan)

    @Query("SELECT * FROM catatan_doggy ORDER BY tanggal DESC")
    fun getCatatan(): Flow<List<Catatan>>

    @Query("SELECT * FROM catatan_doggy WHERE id = :id")
    suspend fun getCatatanById(id: Int): Catatan?

    @Query("DELETE FROM catatan_doggy WHERE id = :id")
    suspend fun deleteById(id: Int)
}