package com.restu.mvvmproject.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.restu.mvvmproject.R
import com.restu.mvvmproject.databinding.ActivityLiveDataBinding

class LiveDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLiveDataBinding
    private lateinit var viewModel: LiveDataViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_live_data)
        viewModel = ViewModelProvider(this).get(LiveDataViewModel::class.java)

        viewModel.count.observe(this, Observer {
          binding.countText.text = it.toString()
        })

        binding.button.setOnClickListener {
           viewModel.updateCount()
        }
    }
}
