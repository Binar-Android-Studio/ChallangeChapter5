package com.example.ui_design_chapter5.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ui_design_chapter5.R
import com.example.ui_design_chapter5.databinding.FragmentRegisterBinding
import com.example.ui_design_chapter5.databinding.FragmentSettingBinding
import java.util.*

class SettingFragment : Fragment() {
    lateinit var binding : FragmentSettingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.engg.setOnClickListener{
            setLocale("en")
            findNavController().navigate(R.id.action_settingFragment_to_listFilmFragment)
        }

        binding.indo.setOnClickListener{
            setLocale("id")
            findNavController().navigate(R.id.action_settingFragment_to_listFilmFragment)
        }
    }

    fun setLocale(lang: String?) {
        val myLocale = Locale(lang)
        val res = resources
        val conf = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, res.displayMetrics)

    }
}