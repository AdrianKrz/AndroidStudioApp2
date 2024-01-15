package com.example.prm4.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "things")
data class ThingsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val produkt: String,
    val adress: String,
    val uri: String
)
