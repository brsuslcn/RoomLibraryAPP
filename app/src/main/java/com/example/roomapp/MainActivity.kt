package com.example.roomapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        binding.btnUpdate.setOnClickListener()
        {
            startActivity(Intent(this,UpdatePerson_Activity::class.java))
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