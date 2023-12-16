package com.alicanadasapplication.app.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "engineers")
data class Muhendisler(
    @PrimaryKey(autoGenerate = true)
    val engineerId: Long = 0,
    var name: String,
    var surname: String,
    var phone: String,
    var numberOfProjects: Int = 0 // Varsayılan değeri 0 olarak ayarlandı.
)