package com.restu.mvvmproject.corountines
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.restu.mvvmproject.R
import com.restu.mvvmproject.databinding.ActivityCorountineBinding
import com.restu.mvvmproject.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class CorountineActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCorountineBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_corountine)

        binding.btnClick.setOnClickListener {
            CoroutineScope(Main).launch {
                Log.i("MyTag","Calculation started....")

                val stock1 = async(IO) {
                    getStock1()
                }
                val stock2 = async(IO) {
                    getStock2()
                }

                val total = stock1.await()+stock2.await()
                Toast.makeText(applicationContext,"Total is $total",Toast.LENGTH_LONG).show()

            }
        }

    }
}

private suspend fun getStock1() : Int {
    delay(10000)
    Log.i("MyTag"," stock 1 returned ")
    return 55000
}

private suspend fun getStock2() : Int {
    delay(8000)
    Log.i("MyTag"," stock 2 returned ")
    return 35000
}