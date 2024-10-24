package com.example.listedetaches.ViewModel

import androidx.lifecycle.ViewModel
import com.example.listedetaches.Task.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TaskViewModel : ViewModel() {
    private val _tasks = MutableStateFlow(List(100) { i -> Task(i, "Task # $i") })
    val tasks: StateFlow<List<Task>> = _tasks

    // Ajouter une nouvelle tâche
    fun addTask() {
        val currentTasks = _tasks.value.toMutableList()
        val newTask = Task(currentTasks.size, "Task # ${currentTasks.size}")
        currentTasks.add(newTask)
        _tasks.value = currentTasks
    }

    // Supprimer une tâche
    fun deleteTask(task: Task) {
        val currentTasks = _tasks.value.toMutableList()
        currentTasks.remove(task)
        _tasks.value = currentTasks
    }

    // Mettre à jour l'état d'une tâche (complétée ou non)
    fun toggleTaskCompletion(task: Task) {
        val currentTasks = _tasks.value.toMutableList()
        val index = currentTasks.indexOf(task)
        if (index != -1) {
            val updatedTask = currentTasks[index].copy(isCompleted = !task.isCompleted)
            currentTasks[index] = updatedTask
            _tasks.value = currentTasks
        }
    }
}
