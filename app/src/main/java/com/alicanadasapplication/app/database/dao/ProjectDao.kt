package com.alicanadasapplication.app.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.alicanadasapplication.app.database.entities.Projects

@Dao
interface ProjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(projects: Projects)

    @Query("SELECT * FROM projects")
    suspend fun getAllProjects(): List<Projects>

    @Query("DELETE FROM projects WHERE projectId = :id")
    suspend fun deleteProjectById(id: Long)

    @Query("DELETE FROM projects")
    suspend fun deleteProject()

    @Update
    suspend fun updateProject(projects: Projects)

    @Query("SELECT * FROM projects WHERE managerId = :engineerId")
    suspend fun getProjectsForEngineer(engineerId: Long): List<Projects>
}