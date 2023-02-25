package com.example.roomapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.roomapp.databinding.ActivityUpdatePersonBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdatePerson_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdatePersonBinding
    private lateinit var db : Database
    private lateinit var pdao : PersonsDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdatePersonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Database.databaseAccess(this)!!
        pdao = db.getPersonsDao()

        getAllID()

        binding.btnPersonUpdate.setOnClickListener()
        {
            binding.apply {
                val job = CoroutineScope(Dispatchers.Main).launch {
                    val updatedPerson = Persons(
                        spnID.selectedItem.toString().toInt(),
                        updName.text.toString(),
                        updSurname.text.toString(),
                        updAge.text.toString().toInt()
                    )
                    pdao.updatePerson(updatedPerson)
                    Toast.makeText(applicationContext, "Updated Successfully!", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }


        binding.spnID.onItemSelectedListener = object : AdapterView.OnItemClickListener,AdapterView.OnItemSelectedListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                binding.apply {
                    val job = CoroutineScope(Dispatchers.Main).launch {
                        val getinfo = pdao.getPersonInfo(binding.spnID.selectedItem.toString().toInt())
                        updName.setText(getinfo.person_name)
                        updSurname.setText(getinfo.person_surname)
                        updAge.setText(getinfo.person_yas.toString())
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

    private fun getAllID()
    {
        val idList = ArrayList<String>()
        val arrayAdapter : ArrayAdapter<String> = ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, idList)
        val job = CoroutineScope(Dispatchers.Main).launch {
            val getAll = pdao.allPersons()
            for(i in getAll)
            {
                idList.add(i.person_id.toString())
            }

            binding.spnID.adapter = arrayAdapter
        }
    }

}