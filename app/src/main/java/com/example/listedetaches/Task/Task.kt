package com.example.listedetaches.Task

data class Task(
    val id: Int,
    val description: String,
    var isCompleted: Boolean = false
)
