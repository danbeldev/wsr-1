package com.example.network.models

data class Catalog(
    val id: Int,
    val name: String,
    val desc: String,
    val price: Int,
    val category: Category
)
