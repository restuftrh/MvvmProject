package com.restu.mvvmproject.retrofitwithcorountine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.TokenWatcher
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.restu.mvvmproject.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_movie.*
import retrofit2.Response

class MovieActivity : AppCompatActivity() {
    private lateinit var retService: MovieService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(MovieService::class.java)

        val responseLiveData:LiveData<Response<Movie>> = liveData {
            val response : Response<Movie> = retService.getMovie()
            emit(response)
        }
        responseLiveData.observe(this, Observer {
            val movieList = it.body()?.results?.listIterator()
            if(movieList!=null){
                while (movieList.hasNext()){
                    val movieItem = movieList.next()
                    val result =" "+"Movie Title : ${movieItem.original_title}"+"\n"+
                            " "+"Movie Rating : ${movieItem.vote_average}"+"\n"+
                            " "+"Release Date Movie : ${movieItem.release_date}"+"\n\n\n"
                    text_view.append(result)
                }
            }
        })

    }




}
