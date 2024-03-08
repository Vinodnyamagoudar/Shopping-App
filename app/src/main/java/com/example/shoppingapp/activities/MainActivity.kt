package com.example.shoppingapp.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R
import com.example.shoppingapp.adapter.CategoryAdapter
import com.example.shoppingapp.model.Category
import com.example.shoppingapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Observe data from ViewModel
        viewModel.categories.observe(this) { categories ->
            setupRecyclerView(categories)
        }

        // Fetch data from JSON file
        viewModel.fetchData(this)

        //favourite list

    }
    private fun setupRecyclerView(categories: List<Category>) {
        val outerRecyclerView: RecyclerView = findViewById(R.id.category_recyclerview)
        outerRecyclerView.layoutManager = LinearLayoutManager(this)
        outerRecyclerView.adapter = CategoryAdapter(categories)
    }
}