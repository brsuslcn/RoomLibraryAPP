package com.example.roomapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db : Database
    private lateinit var pdao : PersonsDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Database.databaseAccess(this)!!
        pdao = db.getPersonsDao()


        loadPersons()

        binding.btnInsert.setOnClickListener()
        {
            startActivity(Intent(this,AddPerson_Activity::class.java))
            finish()
        }

        binding.btnDelete.setOnClickListener()
        {
            startActivity(Intent(this,DeletePerson_Activity::class.java))
            finish()
        }

    }

    fun loadPersons()
    {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val comingList = pdao.allPersons()

            binding.recylerviewLst.adapter = DatabaseAdapter(comingList)
            binding.recylerviewLst.layoutManager=LinearLayoutManager(applicationContext)
            binding.recylerviewLst.setHasFixedSize(true)
        }
    }

}