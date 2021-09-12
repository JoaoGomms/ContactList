package com.portifolio.contactlists.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.portifolio.contactlists.R
import com.portifolio.contactlists.databinding.FragmentListBinding


class ListFragment : Fragment() {

    private var _binding : FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val view = inflater.inflate(R.layout.fragment_list, container, false)
        _binding = FragmentListBinding.inflate(layoutInflater)

        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return binding.root;

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}