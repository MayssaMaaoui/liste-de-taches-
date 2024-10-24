package com.example.listedetaches

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.listedetaches.Task.Task
import com.example.listedetaches.ViewModel.TaskViewModel
import com.example.listedetaches.ui.theme.ListeDeTachesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskListScreen()
        }
    }
}

@Composable
fun TaskListScreen(taskViewModel: TaskViewModel = viewModel()) {
    val tasks by taskViewModel.tasks.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { taskViewModel.addTask() }) {
            Text("Add one")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(tasks.size) { index ->
                TaskRow(task = tasks[index], onTaskChecked = {
                    taskViewModel.toggleTaskCompletion(it)
                }, onDeleteTask = {
                    taskViewModel.deleteTask(it)
                })
            }
        }
    }
}

@Composable
fun TaskRow(task: Task, onTaskChecked: (Task) -> Unit, onDeleteTask: (Task) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = task.description, modifier = Modifier.weight(1f))

        Checkbox(
            checked = task.isCompleted,
            onCheckedChange = { onTaskChecked(task) }
        )

        IconButton(onClick = { onDeleteTask(task) }) {
            androidx.compose.material3.Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Delete task"
            )
        }
    }
}