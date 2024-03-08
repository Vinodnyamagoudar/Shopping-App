package com.example.shoppingapp.model

data class Category(
    val id: Int,
    val items: List<Item>,
    val name: String
)