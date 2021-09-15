package com.portifolio.contactlists.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.portifolio.contactlists.R
import com.portifolio.contactlists.data.ImageToBitmap
import com.portifolio.contactlists.data.model.UserEntity
import com.portifolio.contactlists.data.viewmodel.UserViewModel
import com.portifolio.contactlists.databinding.FragmentUpdateBinding
import kotlinx.coroutines.launch

class UpdateFragment : Fragment() {
    
    private val args by navArgs<UpdateFragmentArgs>()

    private val userViewModel : UserViewModel by viewModels()

    private lateinit var navigateToList : NavDirections

    private var _binding : FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUpdateBinding.inflate(layoutInflater)

        navigateToList = UpdateFragmentDirections.actionUpdateFragmentToListFragment()

        initFields(args.currentUser)

        binding.btnAdd.setOnClickListener{
            updateUser()
        }

        setHasOptionsMenu(true)


        return binding.root

    }

    private fun updateUser(){

            val firstName = binding.editTextUpdateFirstName.text.toString()
            val lastName = binding.editTextUpdateLastName.text.toString()
            val age = Integer.parseInt(binding.editTextUpdateAge.text.toString())

            if (inputCheck(firstName, lastName, binding.editTextUpdateAge.text)){

                lifecycleScope.launch {
                    val updatedUser = UserEntity(args.currentUser.id, firstName, lastName, age, ImageToBitmap.getImageBitmap(requireContext()))

                    userViewModel.updateUser(updatedUser)
                }


                Toast.makeText(requireContext(), "User Updated", Toast.LENGTH_SHORT).show()

                findNavController().navigate(navigateToList)

            } else {

                Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show()

            }
    }

    private fun initFields(currentUser: UserEntity) {

        binding.editTextUpdateFirstName.setText(currentUser.firstName)
        binding.editTextUpdateLastName.setText(currentUser.lastName)
        binding.editTextUpdateAge.setText(currentUser.age.toString())

    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_action){
            deleteUser()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setPositiveButton("Yes"){
                _,_ -> userViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(), "User ${args.currentUser.firstName} Successfully removed", Toast.LENGTH_SHORT).show()


            findNavController().navigate(navigateToList)

        }
        builder.setNegativeButton("No"){
                _,_ ->
        }
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}")
        builder.create().show()
    }

}