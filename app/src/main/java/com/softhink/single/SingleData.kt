package com.softhink.single

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "single_table")
data class SingleData(@PrimaryKey @ColumnInfo(name = "username") val username: String,
                 @ColumnInfo(name = "nombre")  val fullName: String,
                 @ColumnInfo(name = "description") val description: String?,
                 @ColumnInfo(name = "birthdate") val birthdate: String?,
                 @ColumnInfo(name = "email") val email: String,
                 @ColumnInfo(name = "calificacion") val score: String?,
                 @ColumnInfo(name = "Ocupacion") val job: String?,
                 @ColumnInfo(name = "fecha_alta") val fechaAlta: String?,
                 @ColumnInfo(typeAffinity = ColumnInfo.BLOB) val profilePick: ByteArray?)