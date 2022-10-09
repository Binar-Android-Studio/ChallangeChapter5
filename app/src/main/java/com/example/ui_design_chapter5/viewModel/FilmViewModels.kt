package com.example.ui_design_chapter5.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ui_design_chapter5.viewModel.model.movieDatabae
import com.example.ui_design_chapter5.viewModel.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmViewModels : ViewModel() {

    private val liveDataMovieDatabase : MutableLiveData<movieDatabae?> = MutableLiveData()
    private val itemLiveData : MutableLiveData<Item> = MutableLiveData()

    fun getLiveDataMovie() : MutableLiveData<movieDatabae?> = liveDataMovieDatabase
    fun getLiveDataItem () : MutableLiveData<Item> = itemLiveData

    fun showItemListData(){
        ApiService.instance.getDetailFilm()
            .enqueue(object : Callback<movieDatabae>{
                override fun onResponse(
                    call: Call<movieDatabae>,
                    response: Response<movieDatabae>
                ) {
                    if (response.isSuccessful){
                        liveDataMovieDatabase.postValue(response.body())
                    }else{
                        liveDataMovieDatabase.postValue(null)
                        Log.d("notSuccess", response.body().toString())
                    }
                }

                override fun onFailure(call: Call<movieDatabae>, t: Throwable) {
                    liveDataMovieDatabase.postValue(null)
                    Log.d("Failed",t.message.toString())
                }

            })
    }

}