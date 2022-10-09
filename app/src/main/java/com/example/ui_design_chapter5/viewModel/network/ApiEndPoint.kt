package com.example.ui_design_chapter5.viewModel.network

import com.example.ui_design_chapter5.viewModel.model.movieDatabae
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndPoint {

    @GET("3/list/1?api_key=c1bc659015ac6e62beb57434d9793ed9&language=en-US")
    fun getDetailFilm() : Call<movieDatabae>

}