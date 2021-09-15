package com.portifolio.contactlists.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.portifolio.contactlists.data.model.UserEntity
import com.portifolio.contactlists.databinding.CustomRowBinding
import com.portifolio.contactlists.fragments.list.ListFragmentDirections

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    private var contactList = emptyList<UserEntity>()

    inner class ViewHolder(val binding: CustomRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = CustomRowBinding.inflate(LayoutInflater.from(parent.context))

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentUser = contactList[position]

        holder.binding.idTextView.text = currentUser.id.toString()
        holder.binding.firstNameTextView.text = currentUser.firstName
        holder.binding.lastNameTextView.text = currentUser.lastName
        holder.binding.ageTextView.text = currentUser.age.toString()
        holder.binding.imageView.load(currentUser.photo)

        holder.binding.rowLayout.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentUser)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = contactList.size

    fun setData(newList: List<UserEntity>){
        contactList = newList
        notifyDataSetChanged()
    }



}