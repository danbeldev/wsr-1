package com.example.network

import com.example.network.models.Card
import com.example.network.models.Catalog
import com.example.network.models.Category
import com.example.network.models.CreateProject
import com.example.network.models.Project

interface Network {

    suspend fun login(email: String, password: String): Boolean

    suspend fun getAllCategories(): List<Category>

    suspend fun getAllCatalog(): List<Catalog>

    suspend fun getCard(): List<Card>

    suspend fun addCard(catalog: Catalog)

    suspend fun updateCardCount(id: String, count: Int)

    suspend fun getAllProject(): List<Project>

    suspend fun createProject(params: CreateProject)
}