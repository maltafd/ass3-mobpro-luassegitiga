package org.d3if2134.luas_segitiga.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "segitiga")
data class SegitigaEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var alas: Float,
    var tinggi: Float,
    var hasil: Float
)