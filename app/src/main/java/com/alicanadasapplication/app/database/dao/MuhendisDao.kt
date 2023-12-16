package com.alicanadasapplication.app.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.alicanadasapplication.app.database.entities.Muhendisler

@Dao
interface MuhendisDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMuhendis(muhendisler: Muhendisler)

    @Query("SELECT * FROM engineers")
    suspend fun getAllMuhendis(): List<Muhendisler>


    @Query("DELETE FROM engineers WHERE engineerId = :id")
    suspend fun deleteMuhendisById(id: Long)

    @Query("SELECT * FROM engineers WHERE numberOfProjects > 3")
    suspend fun getMuhendisWithMoreThanThreeProjects(): List<Muhendisler>

    @Query("DELETE FROM engineers")
    suspend fun deleteEngineers()

    @Update
    suspend fun updateEngineer(muhendis: Muhendisler)

    @Query("SELECT * FROM engineers WHERE engineerId = :engineerId")
    suspend fun getEngineerById(engineerId: Long): Muhendisler

}

