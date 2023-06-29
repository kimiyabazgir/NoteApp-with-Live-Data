package com.example.roomlivedata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.room.Room
import com.example.roomlivedata.databinding.ActivityNotesBinding
import com.example.roomlivedata.room.db.NoteDatabase
import com.example.roomlivedata.room.utils.NOTE_DATABASE

class NotesActivity : AppCompatActivity() {
    //binding
    private lateinit var binding: ActivityNotesBinding
    //Database
    val noteDb by lazy {
        Room.databaseBuilder(this,NoteDatabase::class.java, NOTE_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    //others
    private lateinit var navController:NavController
    private lateinit var appBarConfiguration:AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            //Navigation
            navController=findNavController(R.id.navHost)
            appBarConfiguration= AppBarConfiguration(navController.graph)
            setupActionBarWithNavController(navController,appBarConfiguration)
        }
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}