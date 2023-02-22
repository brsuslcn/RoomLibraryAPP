package com.example.roomapp

import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.roomapp.databinding.ActivityAddPersonBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddPerson_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPersonBinding
    private lateinit var db: Database
    private lateinit var pdao: PersonsDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddPersonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Database.databaseAccess(this)!!
        pdao = db.getPersonsDao()

        binding.btnPersonAdd.setOnClickListener()
        {
            binding.apply {
                if (TextUtils.isEmpty(edName.text.toString()) || TextUtils.isEmpty(edSurname.text.toString()) || TextUtils.isEmpty(edAge.text.toString()))
                {

                     Toast.makeText(applicationContext, "Please enter all values!", Toast.LENGTH_LONG).show()
                }

                else
                {
                    val job = CoroutineScope(Dispatchers.Main).launch {
                        val addNew = Persons(
                            0,
                            edName.text.toString(),
                            edSurname.text.toString(),
                            edAge.text.toString().toInt()
                        )
                        pdao.addPerson(addNew)
                    }

                    Toast.makeText(applicationContext, "Successfully Added!", Toast.LENGTH_LONG).show()
                }
            }

        }
    }
}


