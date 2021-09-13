package com.portifolio.contactlists.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.portifolio.contactlists.data.UserEntity
import com.portifolio.contactlists.databinding.CustomRowBinding

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    private var contactList = emptyList<UserEntity>()

    inner class ViewHolder(val binding: CustomRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = CustomRowBinding.inflate(LayoutInflater.from(parent.context))

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.idTextView.text = contactList[position].id.toString()
        holder.binding.firstNameTextView.text = contactList[position].firstName
        holder.binding.lastNameTextView.text = contactList[position].lastName
        holder.binding.ageTextView.text = contactList[position].age.toString()
    }

    override fun getItemCount(): Int = contactList.size

    fun setData(newList: List<UserEntity>){
        contactList = newList
        notifyDataSetChanged()
    }



}