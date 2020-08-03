package com.restu.mvvmproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.restu.mvvmproject.corountines.CorountineActivity
import com.restu.mvvmproject.databinding.ActivityMainBinding
import com.restu.mvvmproject.databinding.DataBindingActivity
import com.restu.mvvmproject.livedata.LiveDataActivity
import com.restu.mvvmproject.retrofitwithcorountine.MovieActivity
import com.restu.mvvmproject.roomlibrary.FilmActivity
import com.restu.mvvmproject.viewmodel.ViewModelActivity
import com.restu.mvvmproject.workmanager.WorkManagerActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.dataBindingBtn.setOnClickListener {
            intent = Intent(applicationContext, DataBindingActivity::class.java)
            startActivity(intent)
        }
        binding.viewModelBtn.setOnClickListener {
            intent = Intent(applicationContext, ViewModelActivity::class.java)
            startActivity(intent)
        }
        binding.liveDataBtn.setOnClickListener {
            intent = Intent(applicationContext, LiveDataActivity::class.java)
            startActivity(intent)
        }
        binding.coroutineBtn.setOnClickListener {
            intent = Intent(applicationContext, CorountineActivity::class.java)
            startActivity(intent)
        }
        binding.roomLibraryBtn.setOnClickListener {
            intent = Intent(applicationContext, FilmActivity::class.java)
            startActivity(intent)
        }
        binding.workManagerBtn.setOnClickListener {
            intent = Intent(applicationContext, WorkManagerActivity::class.java)
            startActivity(intent)
        }
        binding.retrofitBtn.setOnClickListener {
            intent = Intent(applicationContext, MovieActivity::class.java)
            startActivity(intent)
        }
    }
}