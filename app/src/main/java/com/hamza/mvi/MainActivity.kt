package com.hamza.mvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hamza.mvi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding?.root)

        send()
        render()
    }

    private fun render() {
        TODO("Not yet implemented")
    }

    private fun send() {
        actions()
    }

    private fun actions() {
        binding.btnAddNumber.setOnClickListener {

            binding.txtNumber.text = (count++).toString()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}