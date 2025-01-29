package ir.mirsobhan.apps.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.mirsobhan.apps.todolist.ui.theme.TODOListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TODOListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var tasklist = listOf<Task>(
                        Task(
                            1,
                            "Initial Setup",
                            "Configure the basic settings and parameters necessary for the project to commence."
                        ),
                        Task(
                            2,
                            "Data Collection",
                            "Gather all relevant data and resources needed to support the project's objectives."
                        ),
                        Task(
                            3,
                            "Team Coordination",
                            "Organize a meeting with team members to align on goals, responsibilities, and timelines."
                        ),
                        Task(
                            4,
                            "Research Phase",
                            "Conduct thorough research on industry standards and best practices to inform project decisions."
                        )
                    )


                    var tsklst = remember { mutableStateOf(tasklist) }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        SearchBox(modifier = Modifier, tasklist) { list ->
                            tsklst.value = list
                        }
                        list_of_tasks(tasks = tsklst)
                    }

                }
            }
        }
    }
}

@Composable
fun SearchBox(modifier: Modifier = Modifier, list: List<Task>, onChange: (List<Task>) -> Unit) {
    var strings = remember { mutableStateOf("") }
    OutlinedTextField(strings.value, modifier = modifier, onValueChange = { string: String ->
        strings.value = string
        var filterd_list = list.filter { task ->
            task.name.contains(string)
        }
        onChange(filterd_list)
    })
}


@Composable
fun list_of_tasks(modifier: Modifier = Modifier, tasks: MutableState<List<Task>>) {
    LazyColumn(modifier.padding(10.dp), contentPadding = PaddingValues(25.dp)) {
        items(tasks.value) { task ->
            task_card(task = task)
            Spacer(Modifier.padding(20.dp))

        }
    }
}

@Composable
fun task_card(modifier: Modifier = Modifier, task: Task) {
    Card(
        modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Text(task.id.toString(), modifier = Modifier.padding(20.dp).weight(1f))

            Column(Modifier.weight(3f)) {
                Text(task.name)
                Text(task.description)
            }
            Button(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(50.dp)
                    .weight(1f)
                    .aspectRatio(0.1f),
                onClick = {
                    task.completed = true
                }) {
                Icon(Icons.Rounded.Check, contentDescription = "check task", modifier = Modifier.size(50.dp))
            }
        }

    }
}