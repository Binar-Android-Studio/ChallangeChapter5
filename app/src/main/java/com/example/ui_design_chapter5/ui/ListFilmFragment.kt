package com.example.ui_design_chapter5.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ui_design_chapter5.R
import com.example.ui_design_chapter5.adapter.MovieAdapter
import com.example.ui_design_chapter5.databinding.FragmentListFilmBinding
import com.example.ui_design_chapter5.viewModel.FilmViewModels
import com.example.ui_design_chapter5.viewModel.Item
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar


class ListFilmFragment : Fragment(), MovieAdapter.ListMovieInterface {
    private var _binding : FragmentListFilmBinding? = null
    private val binding get() = _binding!!
    lateinit var  sharedPrefs : SharedPreferences

    private  val MovieViewModel : FilmViewModels by viewModels()
    private lateinit var adapter : MovieAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListFilmBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.topAppBar.setNavigationOnClickListener {
            binding.drawerLayout.open()
        }

        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item selected
            menuItem.isChecked = true
            when(menuItem.itemId){
                R.id.account -> {
                    findNavController().navigate(R.id.action_listFilmFragment_to_accountFragment)
                }
                R.id.setting -> {
                    findNavController().navigate(R.id.action_listFilmFragment_to_settingFragment)
                }
                R.id.logout -> {
                    sharedPrefs = requireActivity().getSharedPreferences("registerData", Context.MODE_PRIVATE)
                    var addData = sharedPrefs.edit()
                    addData.putString("usernamelgn", null)
                    addData.putString("passwordlgn", null)
                    addData.apply()
                    findNavController().navigate(R.id.action_listFilmFragment_to_loginFragment)
                }
            }
            binding.drawerLayout.close()
            true
        }


        setRecycleView()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setRecycleView(){
        adapter = MovieAdapter(this)

        binding.apply {
            MovieViewModel.showItemListData()
            MovieViewModel.getLiveDataMovie().observe(viewLifecycleOwner){
                if ( it != null){
//                    progressBar.visibility = View.GONE
                    adapter.setData(it.items)
                }else{
//                    progressBar.visibility = View.GONE
                    Snackbar.make(binding.root, "Data Gagal Dimuat", Snackbar.LENGTH_SHORT)
                        .setBackgroundTint(ContextCompat.getColor(requireContext(),
                            R.color.colorPrimary
                        ))
                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
                        .show()
                }
            }
            rvPost.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rvPost.adapter = adapter

        }
    }


    override fun onItemClick(MovieDetail: Item) {
        TODO("Not yet implemented")
    }

}