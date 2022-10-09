package com.example.ui_design_chapter5.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ui_design_chapter5.R
import com.example.ui_design_chapter5.databinding.ListFilmBinding
import com.example.ui_design_chapter5.ui.ListFilmFragmentDirections
import com.example.ui_design_chapter5.viewModel.Item

class MovieAdapter (private var ListMovie : ListMovieInterface) : RecyclerView.Adapter<MovieAdapter.ViewHolder>(){

    private val differCallback = object : DiffUtil.ItemCallback<Item>(){
        override fun areItemsTheSame(
            oldItem: Item,
            newItem: Item
        ): Boolean {
            return oldItem.id == oldItem.id
        }

        override fun areContentsTheSame(
            oldItem: Item,
            newItem: Item
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, differCallback)




    inner class ViewHolder(var binding : ListFilmBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(MovieDetail : Item){
            binding.dataMovie = MovieDetail

            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w500/" + MovieDetail.backdropPath)
                .into(binding.imageview)

            binding.card.setOnClickListener{
                var bund = Bundle()
                MovieDetail.id?.let { it1 -> bund.putInt("id", it1) }
                MovieDetail.originalTitle?.let { it1 -> bund.putString("originalTitle", it1) }
                MovieDetail.title?.let { it1 -> bund.putString("title", it1) }
                MovieDetail.posterPath?.let { it1 -> bund.putString("posterPath", it1) }
                MovieDetail.popularity?.let { it1 -> bund.putDouble("popularity", it1) }
                MovieDetail.overview?.let { it1 -> bund.putString("overview", it1) }
                Navigation.findNavController(it).navigate(R.id.action_listFilmFragment_to_detailFragment2, bund)
            }

            itemView.setOnClickListener{
                ListMovie.onItemClick(MovieDetail)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListFilmBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val film = differ.currentList[position]
        holder.bind(film)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setData(data : List<Item>){
        differ.submitList(data)
    }

    interface ListMovieInterface {
        fun onItemClick(MovieDetail: Item)
    }
}