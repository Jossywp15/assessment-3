package org.d3if3169.doghotel.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "catatan_doggy")
data class Catatan(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nama: String,
    val catatan: String,
    val lamaMenginap: String,
    val tanggal: String
)
