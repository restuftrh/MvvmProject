package com.restu.mvvmproject.roomlibrary


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.restu.mvvmproject.R
import com.restu.mvvmproject.databinding.ListItemBinding
import com.restu.mvvmproject.roomlibrary.db.Film

class MyRecyclerViewAdapter(private val clickListener:(Film)->Unit)
    : RecyclerView.Adapter<MyViewHolder>()
{
    private val filmList = ArrayList<Film>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val layoutInflater = LayoutInflater.from(parent.context)
      val binding : ListItemBinding =
          DataBindingUtil.inflate(layoutInflater, R.layout.list_item,parent,false)
      return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return filmList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      holder.bind(filmList[position],clickListener)
    }

    fun setList(film: List<Film>) {
        filmList.clear()
        filmList.addAll(film)

    }

}

class MyViewHolder(val binding: ListItemBinding):RecyclerView.ViewHolder(binding.root){

    fun bind(film: Film,clickListener:(Film)->Unit){
          binding.nameTextView.text = film.name
          binding.gendreTextView.text = film.gendre
          binding.ratingTextView.text = film.rating
          binding.listItemLayout.setOnClickListener{
             clickListener(film)
          }
    }
}