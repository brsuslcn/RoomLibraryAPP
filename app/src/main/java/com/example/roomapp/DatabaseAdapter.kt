package com.example.roomapp


import android.view.LayoutInflater
import android.view.ViewGroup
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
                binding.apply{
                val currentItem = personList[position]
                txtName.text = currentItem.person_name
                txtSurname.text = currentItem.person_surname
                txtAge.text = currentItem.person_yas.toString()
                txtID.text = currentItem.person_id.toString()
                imageView.setImageResource(R.drawable.avatar)
        }
    }

    override fun getItemCount() = personList.size



}
