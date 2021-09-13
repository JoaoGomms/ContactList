package com.portifolio.contactlists.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.portifolio.contactlists.R
import com.portifolio.contactlists.adapter.ContactAdapter
import com.portifolio.contactlists.data.UserViewModel
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
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        initObservers()
        setupRecyclerView()


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

}