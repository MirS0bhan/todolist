package ir.mirsobhan.apps.todolist

data class Task(val id: Int, val name: String, val description: String, var completed: Boolean = false)


