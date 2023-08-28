package com.hamza.mvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.hamza.mvi.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val viewModel: AddNumberViewModel by lazy {
        ViewModelProviders.of(this).get(AddNumberViewModel::class.java)
    }
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding?.root)

        actions()
        render()
    }


    private fun send() {
        lifecycleScope.launch {
            viewModel.channel.send(MainIntent.AddNumber)
        }
    }

    private fun render() {
        lifecycleScope.launchWhenStarted {
            viewModel.stateFlow.collect {
                when (it) {
                    is MainViewState.Idle -> binding.txtNumber.text = "Idle"
                    is MainViewState.Result ->binding.txtNumber.text = it.number.toString()
                    is MainViewState.Error ->binding.txtNumber.text =it.message
                }
            }
        }
    }

    private fun actions() {
        binding.btnAddNumber.setOnClickListener {

            send()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}