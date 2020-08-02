package com.restu.mvvmproject.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.restu.mvvmproject.R
import com.restu.mvvmproject.databinding.ActivityVewModelBinding

class ViewModelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVewModelBinding
    private lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vew_model)
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        binding.resultTextView.text = viewModel.getTotal().toString()

        binding.insertButton.setOnClickListener {
            viewModel.setTotal(binding.inputEditText.text.toString().toInt())
            binding.resultTextView.text = viewModel.getTotal().toString()

        }

    }
}
