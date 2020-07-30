package com.restu.mvvmproject.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.restu.mvvmproject.R
import com.restu.mvvmproject.databinding.ActivityMainBinding

class DataBindingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataBindingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding)

        binding.btnClick.setOnClickListener {
            binding.apply {
                if (linear.visibility == View.GONE) {
                    linear.visibility = View.VISIBLE
                } else {
                    linear.visibility = View.GONE
                }
            }
        }
    }
}