package com.example.prm4.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.prm4.data.model.ThingsEntity

@Dao
interface ThingsDao {
    @Query("SELECT * FROM things;")
    fun getAll(): List<ThingsEntity>

    @Query("SELECT * FROM things ORDER BY produkt ASC;")
    fun getAllSortedByName(): List<ThingsEntity>

    @Insert
    fun addKierowca(newDish: ThingsEntity)

    @Delete
    fun removeKierowca(kierowca: ThingsEntity)

    @Query("DELETE FROM things WHERE id = :id")
    fun removeById(id: Int)

    @Update
    fun updateKierowca(newDish: ThingsEntity)

    @Query("SELECT * FROM things WHERE id = :id")
    fun getById(id: Long): ThingsEntity?
}