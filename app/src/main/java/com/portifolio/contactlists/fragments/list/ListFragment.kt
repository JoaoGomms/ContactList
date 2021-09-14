package com.portifolio.contactlists.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.portifolio.contactlists.R
import com.portifolio.contactlists.adapter.ContactAdapter
import com.portifolio.contactlists.data.viewmodel.UserViewModel
import com.portifolio.contactlists.databinding.FragmentListBinding


class ListFragment : Fragment() {

    private val userViewModel: UserViewModel by viewModels()
    private val contactListAdapter by lazy { ContactAdapter() }

    private var _binding : FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListBinding.inflate(layoutInflater)

        binding.floatingActionButton.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToAddFragment()
            findNavController().navigate(action)
        }

        initObservers()
        setupRecyclerView()

        setHasOptionsMenu(true)

        return binding.root;

    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = contactListAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initObservers(){
        userViewModel.getReadAllData().observe(viewLifecycleOwner, {
            contactListAdapter.setData(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId.equals(R.id.delete_action)){
            val builder = AlertDialog.Builder(requireContext())

            builder.setTitle("Delete All Users")
            builder.setMessage("Are you sure that you want to delete all users from this list?")

            builder.setPositiveButton("Yes") {
                    _,_ ->
                userViewModel.deleteAllUsers()
                Toast.makeText(requireContext(), "All users deleted", Toast.LENGTH_SHORT).show()

            }
            builder.setNegativeButton("No") { _,_ -> }

            builder.create().show()
        }

        return super.onOptionsItemSelected(item)
    }
}