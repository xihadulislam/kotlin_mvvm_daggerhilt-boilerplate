package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.screens.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.flexbox.*
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.utils.ext.EventObserver
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var productAdapter: ProductAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.apiRequestSuccess.observe(this, EventObserver {
            Toast.makeText(this, "Success  ${it.size}", Toast.LENGTH_SHORT).show()
            productAdapter.submitList(it)
            binding.progressBar.visibility = View.GONE
        })

        viewModel.apiRequestFailed.observe(this, EventObserver {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            binding.progressBar.visibility = View.GONE
        })

        viewModel.fetchAllProductsFromSever()

        val layoutManager = LinearLayoutManager(this)
        binding.productList.adapter = productAdapter
        binding.productList.layoutManager = layoutManager


    }
}