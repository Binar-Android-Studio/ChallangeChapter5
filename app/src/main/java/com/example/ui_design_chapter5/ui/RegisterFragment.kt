package com.example.ui_design_chapter5.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.ui_design_chapter5.R
import com.example.ui_design_chapter5.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    lateinit var binding : FragmentRegisterBinding
    lateinit var  sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireActivity().getSharedPreferences("registerData", Context.MODE_PRIVATE)

        binding.register.setOnClickListener{
            registerBtn()
        }

        binding.tvLogin.setOnClickListener{
            loginBtn()
        }
    }
    fun registerBtn(){
        val username = binding.isiUsername.text.toString()
        val fullName = binding.isiFullname.text.toString()
        val password = binding.isiPassword.text.toString()
        val password2 = binding.isiPassword2.text.toString()
        if(password == password2){
            var addData = sharedPreferences.edit()
            addData.putString("username", username)
            addData.putString("fullName", fullName)
            addData.putString("password", password)
            addData.apply()
            Toast.makeText(requireContext(), "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        else
            Toast.makeText(requireContext(), "Password Yang Dimasukkan Tidak Sama", Toast.LENGTH_SHORT).show()

    }
    fun loginBtn(){
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }
}