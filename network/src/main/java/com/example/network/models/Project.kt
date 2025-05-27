package com.example.network.models

data class Project(
    val id: String,
    val name: String,
    val type: ProjectType,
    val startDate: String,
    val endDate: String,
    val categoryId: Int,
    val to: String,
    val desc: String
)

data class CreateProject(
    val name: String,
    val type: ProjectType,
    val startDate: String,
    val endDate: String,
    val categoryId: Int,
    val to: String,
    val desc: String
)

enum class ProjectType {
    TEST_1,
    TEST_2
}
