package com.example.network

import com.example.network.models.Card
import com.example.network.models.Catalog
import com.example.network.models.Category
import com.example.network.models.CreateProject
import com.example.network.models.Project
import java.util.UUID

class NetworkImpl: Network {

    private val card = mutableListOf<Card>()
    private val project = mutableListOf<Project>()

    override suspend fun login(email: String, password: String): Boolean {
        return email == "admin@bk.ru" && password == "oNQZn zHx2"
    }

    override suspend fun getAllCategories(): List<Category> {
        return listOf(
            Category(
                id = 1,
                name = "Популярные",
            ),
            Category(
                id = 2,
                name = "Женщинам",
            ),
            Category(
                id = 3,
                name = "Мужчинам",
            )
        )
    }

    override suspend fun getAllCatalog(): List<Catalog> {
        return listOf(
            Catalog(
                id = 1,
                name = "Рубашка Воскресенье для машинного вязания",
                price = 300,
                category = getAllCategories()[0],
                desc = "Мой выбор для этих шапок – кардные составы, которые раскрываются деликатным пушком. Кашемиры, мериносы, смесовки с ними отлично подойдут на шапку. Кардные составы берите в большое количество сложений, вязать будем резинку 1х1, плотненько. Пряжу 1400-1500м в 100г в 4 сложения, пряжу 700м в 2 сложения. Ориентир для конечной толщины – 300-350м в 100г. Артикулы, из которых мы вязали эту модель: Zermatt Zegna Baruffa, Cashfive, Baby Cashmere Loro Piana, Soft Donegal и другие. Примерный расход на шапку с подгибом 70-90г."
            ),
            Catalog(
                id = 2,
                name = "Шорты вторник для машинного вязания",
                price = 300,
                category = getAllCategories()[1],
                desc = "test"
            )
        )
    }

    override suspend fun getCard(): List<Card> {
        return card
    }

    override suspend fun addCard(catalog: Catalog) {
        card.add(Card(UUID.randomUUID().toString(), 1, catalog))
    }

    override suspend fun updateCardCount(
        id: String,
        count: Int
    ) {
        card.forEach {
            if (it.id == id) {
                it.count = count
                return
            }
        }
    }

    override suspend fun getAllProject(): List<Project> {
        return project
    }

    override suspend fun createProject(params: CreateProject) {
        project.add(
            Project(
                id = UUID.randomUUID().toString(),
                name = params.name,
                startDate = params.startDate,
                endDate = params.endDate,
                to = params.to,
                desc = params.desc,
                categoryId = params.categoryId,
                type = params.type
            )
        )
    }
}