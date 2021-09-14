package com.portifolio.contactlists.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.portifolio.contactlists.data.model.UserEntity
import com.portifolio.contactlists.data.viewmodel.UserViewModel
import com.portifolio.contactlists.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private val mUserViewModel: UserViewModel by viewModels()

    private var _binding : FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddBinding.inflate(layoutInflater)

        binding.btnAdd.setOnClickListener{
            insertDatatoDatabase()
        }
        return binding.root
    }

    private fun insertDatatoDatabase() {

        val firstName = binding.editTextFirstName.text.toString()
        val lastName = binding.editTextLastName.text.toString()
        val age = binding.editTextAge.text

        if(inputCheck(firstName, lastName, age)){

            val user = UserEntity(0, firstName, lastName, Integer.parseInt(age.toString()))

            mUserViewModel.addUser(user)

            Toast.makeText(requireContext(), "Adicionado", Toast.LENGTH_SHORT).show()

            val action = AddFragmentDirections.actionAddFragmentToListFragment()
            findNavController().navigate(action)

        } else {
            Toast.makeText(requireContext(), "Campos nao podem ser vazios", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}