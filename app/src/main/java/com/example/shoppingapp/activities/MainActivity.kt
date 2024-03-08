package com.example.shoppingapp.activities

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R
import com.example.shoppingapp.adapter.CategoryAdapter
import com.example.shoppingapp.model.Category
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Parse JSON data and set up RecyclerView
        val categories = readJsonData(this)
        setupRecyclerView(categories)
    }

    private fun setupRecyclerView(categories: List<Category>) {
        val outerRecyclerView: RecyclerView = findViewById(R.id.category_recyclerview)
        outerRecyclerView.layoutManager = LinearLayoutManager(this)
        outerRecyclerView.adapter = CategoryAdapter(categories)
    }

    fun readJsonData(context: Context): List<Category> {
        try {
            val jsonFile = context.assets.open("data.json")
            val jsonString = jsonFile.bufferedReader().use { it.readText() }
            val gson = Gson()
            val jsonObject = JSONObject(jsonString)
            val categoriesArray = jsonObject.getJSONArray("categories")
            val categoriesType = object : TypeToken<List<Category>>() {}.type
            return gson.fromJson(categoriesArray.toString(), categoriesType)
        } catch (e: IOException) {
            e.printStackTrace()
            return emptyList()
        }
    }
}