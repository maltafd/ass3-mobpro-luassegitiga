package org.d3if2134.luas_segitiga.model

import org.d3if2134.luas_segitiga.db.SegitigaEntity

fun SegitigaEntity.hitungSegitiga(): Hasil {
    val hasil = alas * tinggi / 2

    return Hasil(hasil)
}