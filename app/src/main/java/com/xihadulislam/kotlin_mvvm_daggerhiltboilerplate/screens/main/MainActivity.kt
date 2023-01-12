package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.screens.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.utils.ext.EventObserver
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.apiRequestSuccess.observe(this, EventObserver {
            Toast.makeText(this, "Success  ${it.size}", Toast.LENGTH_SHORT).show()
        })

        viewModel.apiRequestFailed.observe(this, EventObserver {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        binding.hello.setOnClickListener {
            viewModel.fetchAllProductsFromSever()
        }

    }
}