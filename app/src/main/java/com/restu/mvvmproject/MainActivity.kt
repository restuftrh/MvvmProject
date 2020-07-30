package com.restu.mvvmproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.restu.mvvmproject.databinding.ActivityMainBinding
import com.restu.mvvmproject.databinding.DataBindingActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.dataBindingBtn.setOnClickListener {
            intent = Intent(applicationContext, DataBindingActivity::class.java)
            startActivity(intent)
        }
    }
}