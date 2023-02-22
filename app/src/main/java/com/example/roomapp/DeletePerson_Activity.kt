package com.example.roomapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.roomapp.databinding.ActivityAddPersonBinding
import com.example.roomapp.databinding.ActivityDeletePersonBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DeletePerson_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityDeletePersonBinding
    private lateinit var db : Database
    private lateinit var pdao : PersonsDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeletePersonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Database.databaseAccess(this)!!
        pdao = db.getPersonsDao()

        getALL()
        
    }

    fun getALL()
    {
        val idList = ArrayList<String>()
        val job = CoroutineScope(Dispatchers.Main).launch {
            val getAll = pdao.allPersons()
            for (i in getAll)
            {
                idList.add(i.person_id.toString())
            }
        }

        idList.add("NONE")
        val arrayAdapter : ArrayAdapter<String> = ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,idList)
        binding.spnID.adapter = arrayAdapter
    }
}