package ir.mirsobhan.apps.todolist

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf

import androidx.lifecycle.ViewModel


class MainScreenViewModel: ViewModel() {
    val taskList = mutableStateListOf<Task>()
    private var filterString: String by mutableStateOf("")
    var addDialogShow by mutableStateOf(false)

    init {
        addTask(Task("sobhan"))
    }

    fun addTask(task: Task) {
        taskList.add(task)
    }

    fun filterTask(str: String) {
        filterString = str
    }
}