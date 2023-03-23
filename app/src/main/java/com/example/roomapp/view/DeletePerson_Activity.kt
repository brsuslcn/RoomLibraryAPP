package com.example.roomapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.roomapp.repository.Database
import com.example.roomapp.MainActivity
import com.example.roomapp.model.Persons
import com.example.roomapp.data.PersonsDao
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

        getALLID()

        binding.btnPersonDelete.setOnClickListener()
        {

                val job = CoroutineScope(Dispatchers.Main).launch {
                    binding.apply {
                        val deletedPerson = Persons(spnID.selectedItem.toString().toInt(),"person","person",0)
                        pdao.deletePerson(deletedPerson)

                        getALLID()

                    }
                    binding.spnID.setSelection(0)
                    binding.dName.text = "SELECT"
                    binding.dSurname.text = "SELECT"
                    binding.dAge.text = "SELECT"

                }
        }



        binding.spnID.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                binding.apply {
                    val job = CoroutineScope(Dispatchers.Main).launch {
                        val getInfo = pdao.getPersonInfo(binding.spnID.selectedItem.toString().toInt())
                        dName.text = getInfo.person_name
                        dSurname.text = getInfo.person_surname
                        dAge.text = getInfo.person_yas.toString()
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }


    fun getALLID()
    {
        val idList = ArrayList<String>()
        val arrayAdapter : ArrayAdapter<String> = ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,idList)
        val job = CoroutineScope(Dispatchers.Main).launch {
            val getAll = pdao.allPersons()
            for (i in getAll)
            {
                idList.add(i.person_id.toString())
            }

            binding.spnID.adapter = arrayAdapter
        }

    }
}