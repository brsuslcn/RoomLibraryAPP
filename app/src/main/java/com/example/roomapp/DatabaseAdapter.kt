package com.example.roomapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.databinding.ContentMainBinding

private lateinit var binding: ContentMainBinding

class DatabaseAdapter(private val personList : List<Persons>) : RecyclerView.Adapter<DatabaseAdapter.MyViewHolder>() {
    class MyViewHolder(binding : ContentMainBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ContentMainBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = personList[position]
                binding.apply{
                txtName.text = currentItem.person_name
                txtSurname.text = currentItem.person_surname
                txtAge.text = currentItem.person_yas.toString()
        }
    }

    override fun getItemCount() = personList.size



}
