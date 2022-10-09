package com.example.ui_design_chapter5.ui

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ui_design_chapter5.R
import com.example.ui_design_chapter5.databinding.FragmentAccountBinding
import com.example.ui_design_chapter5.databinding.FragmentSplashscreenBinding

class Splashscreen : Fragment() {
    lateinit var binding: FragmentSplashscreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashscreenBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var data = requireActivity().getSharedPreferences("registerData", Context.MODE_PRIVATE)
        var username = data.getString("usernamelgn", null)

        Handler(Looper.myLooper()!!).postDelayed({
            if(username != null)
                findNavController().navigate(R.id.action_splashscreen_to_listFilmFragment)
            else
                findNavController().navigate(R.id.action_splashscreen_to_loginFragment)
        },1000)
    }

}