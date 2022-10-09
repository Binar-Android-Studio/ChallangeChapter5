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
import com.example.ui_design_chapter5.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    lateinit var binding : FragmentLoginBinding
    lateinit var  sharedPrefs : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvRegister.setOnClickListener{
            registerBtn()
        }

        binding.loginButton.setOnClickListener{
           loginBtn()
        }
    }
    fun registerBtn(){
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    fun loginBtn(){
        sharedPrefs = requireActivity().getSharedPreferences("registerData", Context.MODE_PRIVATE)

        var usernameData = sharedPrefs.getString("username", null)
        var passwordData = sharedPrefs.getString("password", null)

        var usernamelgn = binding.textUsername.text.toString()
        var passwordlgn = binding.textPassword.text.toString()

        if (usernamelgn == usernameData && passwordData == passwordlgn){
            var addData = sharedPrefs.edit()
            addData.putString("usernamelgn", usernamelgn)
            addData.putString("passwordlgn", passwordlgn)
            addData.apply()
            findNavController().navigate(R.id.action_loginFragment_to_listFilmFragment)
        }
        else
            Toast.makeText(requireContext(), "Username Dan Password Tidak Cocok !!", Toast.LENGTH_SHORT).show()
    }
}