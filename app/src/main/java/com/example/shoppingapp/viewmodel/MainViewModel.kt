package com.example.shoppingapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppingapp.model.Category
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.io.IOException

class MainViewModel : ViewModel() {
    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> = _categories

    // Method to fetch data from JSON file
    fun fetchData(context: Context) {
        val categoriesList = readJsonData(context)
        _categories.value = categoriesList
    }

    private fun readJsonData(context: Context): List<Category> {
        return try{
            val jsonFile = context.assets.open("data.json")
            val jsonString = jsonFile.bufferedReader().use { it.readText() }
            val gson = Gson()
            val jsonObject = JSONObject(jsonString)
            val categoriesArray = jsonObject.getJSONArray("categories")
            val categoriesType = object : TypeToken<List<Category>>() {}.type
            gson.fromJson(categoriesArray.toString(), categoriesType)
        } catch (e: IOException) {
            e.printStackTrace()
            emptyList()
        }
    }
}