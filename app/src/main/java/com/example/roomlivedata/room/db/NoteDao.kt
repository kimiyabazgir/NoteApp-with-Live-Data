package com.example.roomlivedata.room.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomlivedata.room.utils.NOTE_TABLE

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNote(note:NoteEntity)
    @Query("SELECT * FROM $NOTE_TABLE")
    //میتونستیم1 رو بزنیم ولی من میخوام در قالب لایو دیتا اپدیت شده لیستم رو هر تغییری روو نوت انتیتی بوجود میاد رو  دریافت بکنم درجا لیستم اپدیت بشه
    //1-fun getAllNotes():MutableList<NoteEntity>
    fun getAllNotes():LiveData<MutableList<NoteEntity>>
}