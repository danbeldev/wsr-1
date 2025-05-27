package com.example.network.models

data class Card(
    val id: String,
    var count: Int,
    val catalog: Catalog
)