package com.alicanadasapplication.app.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "projects",
    foreignKeys = [
        ForeignKey(
            entity = Muhendisler::class,
            parentColumns = ["engineerId"],
            childColumns = ["managerId"],
            onDelete = CASCADE
        )
    ]
)
data class Projects(
    @PrimaryKey(autoGenerate = true)
    val projectId: Long = 0,
    val title: String,
    val managerId: Long,
    val content: String
)