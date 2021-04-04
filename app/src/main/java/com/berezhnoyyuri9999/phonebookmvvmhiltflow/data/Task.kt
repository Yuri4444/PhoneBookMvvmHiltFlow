package com.berezhnoyyuri9999.phonebookmvvmhiltflow.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat

@Entity(tableName = "task_table")
@Parcelize
data class Task(

    @ColumnInfo
    val name: String,

    @ColumnInfo
    val important: Boolean = false,

    @ColumnInfo
    val completed: Boolean = false,

    @ColumnInfo
    val created: Long = System.currentTimeMillis(),

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

) : Parcelable {
    val createdDateFormat: String
        get() = DateFormat.getTimeInstance().format(created)
}