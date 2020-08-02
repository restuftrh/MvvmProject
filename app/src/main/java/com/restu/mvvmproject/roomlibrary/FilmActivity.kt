package com.restu.mvvmproject.roomlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.restu.mvvmproject.R
import com.restu.mvvmproject.databinding.ActivityFilmBinding
import com.restu.mvvmproject.roomlibrary.db.Film
import com.restu.mvvmproject.roomlibrary.db.FilmDatabase
import com.restu.mvvmproject.roomlibrary.db.FilmRepository

class FilmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFilmBinding
    private lateinit var filmViewModel: FilmViewModel
    private lateinit var adapter: MyRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_film)
        val dao = FilmDatabase.getInstance(application).filmDAO
        val repository = FilmRepository(dao)
        val factory = FilmViewModelFactory(repository)
        filmViewModel = ViewModelProvider(this,factory).get(FilmViewModel::class.java)
        binding.myViewModel = filmViewModel
        binding.lifecycleOwner = this
        initRecyclerView()

        filmViewModel.message.observe(this, Observer {
         it.getContentIfNotHandled()?.let {
             Toast.makeText(this, it, Toast.LENGTH_LONG).show()
         }
        })

    }

   private fun initRecyclerView(){
       binding.subscriberRecyclerView.layoutManager = LinearLayoutManager(this)
       adapter = MyRecyclerViewAdapter({selectedItem:Film->listItemClicked(selectedItem)})
       binding.subscriberRecyclerView.adapter = adapter
       displaySubscribersList()
   }

    private fun displaySubscribersList(){
        filmViewModel.subscribers.observe(this, Observer {
            Log.i("MYTAG",it.toString())
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(film: Film){
        //Toast.makeText(this,"selected name is ${subscriber.name}",Toast.LENGTH_LONG).show()
        filmViewModel.initUpdateAndDelete(film)
    }
}
