package org.d3if2134.luas_segitiga.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SegitigaDao {
    @Insert
    fun insert(segitiga: SegitigaEntity)
    @Query("SELECT * FROM segitiga ORDER BY id DESC")
    fun getLastSegitiga(): LiveData<List<SegitigaEntity>>
    @Query("DELETE FROM segitiga")
    fun clearData()
}