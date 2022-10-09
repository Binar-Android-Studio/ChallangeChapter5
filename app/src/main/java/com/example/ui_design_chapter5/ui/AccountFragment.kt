package com.example.ui_design_chapter5.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.example.ui_design_chapter5.R
import com.example.ui_design_chapter5.databinding.FragmentAccountBinding
import com.example.ui_design_chapter5.databinding.FragmentDetailBinding

class AccountFragment : Fragment() {
    lateinit var binding : FragmentAccountBinding
    lateinit var  sharedPrefs : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAccountBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPrefs = requireActivity().getSharedPreferences("registerData", Context.MODE_PRIVATE)

        var getusername = sharedPrefs.getString("username", null)
        var getfullname = sharedPrefs.getString("fullName", null)

        binding.namaAsli.setText(getfullname)
        binding.usernameAsli.setText(getusername)

        binding.backbutton.setOnClickListener{
            findNavController().navigate(R.id.action_accountFragment_to_listFilmFragment)
        }
    }

}