package com.example.ucp2.Data.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Supplier")
data class Supplier(
    @PrimaryKey
    val id: String,
    val nama: String,
    val kontak: String,
    val alamat: String,
)
