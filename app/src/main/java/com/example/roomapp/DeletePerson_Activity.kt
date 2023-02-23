package com.example.roomapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
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

        getALLID()



        binding.spnID.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                binding.apply {
                    if(p2!=0)
                    {
                        val job = CoroutineScope(Dispatchers.Main).launch {
                            var getInfo = pdao.getPersonInfo(p2)
                            dName.text =getInfo.person_name
                            dSurname.text = getInfo.person_surname
                            dAge.text = getInfo.person_yas.toString()
                        }
                    }

                }



            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

    }


    fun getALLID()
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