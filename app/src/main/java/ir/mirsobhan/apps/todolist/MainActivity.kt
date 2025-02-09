package ir.mirsobhan.apps.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.mirsobhan.apps.todolist.ui.theme.TODOListTheme
val LightRed = Color(0xfffafaff) // Light red color
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TODOListTheme {
                if (viewModel.addDialogShow) {
                    addDialog({
                        viewModel.addDialogShow = false
                    }) { task -> viewModel.addTask(task);viewModel.addDialogShow = false }
                }
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Box(Modifier.background(LightRed).padding(24.dp)){
                        Column {
                            Box(Modifier.weight(0.5f).fillMaxSize()) {
                                Column {
                                    Row {
                                        IconButton(onClick = {}) {
                                            Icon(Icons.Default.Menu, "menu")
                                        }
                                        IconButton(onClick = {}) {
                                            Icon(Icons.Default.Search, "menu")
                                        }
                                        IconButton(onClick = {}) {
                                            Icon(Icons.Default.Info, "menu")
                                        }
                                    }

                                    titleComponent()
                                }
                            }

                            Column(Modifier.weight(0.5f), horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("task list", Modifier.align(Alignment.Start))
                                listOfTasks(tasks = viewModel.taskList)
                            }
                        }
                        FloatingActionButton(
                            onClick = { viewModel.addDialogShow = true },
                            Modifier.align(Alignment.BottomEnd).padding(16.dp),
                            CircleShape,
                            containerColor = Color.Blue,
                            contentColor = Color.White
                        ) {
                            Icon(Icons.Default.Add, contentDescription = "", tint = Color.White)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun searchBox(modifier: Modifier = Modifier, onChange: (String) -> Unit) {
    var strings by remember { mutableStateOf("") }
    OutlinedTextField(strings, modifier = modifier, onValueChange = { string: String ->
        strings = string
        onChange(string)
    }
    )
}


@Composable
fun listOfTasks(modifier: Modifier = Modifier, tasks: SnapshotStateList<Task>) {
    LazyColumn(modifier){
        items(tasks) { task ->
            taskCard(task = task)
            Spacer(Modifier.padding(5.dp))

        }
    }
}

@Composable
fun taskCard(modifier: Modifier = Modifier, task: Task) {
    var checkComplete by remember { mutableStateOf(task.completed) }

    Box(
        modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp)).background(Color.White)){
        Row(Modifier.padding(15.dp), Arrangement.Center, Alignment.CenterVertically) {
            circularCheckBox(checkComplete, Modifier, Color.Cyan, 32.dp, CircleShape) {
                checkComplete = !checkComplete
            }
            Spacer(Modifier.width(24.dp))
            Text(task.title, style = getTextStyle(checkComplete), fontSize = 24.sp)
        }
    }
}


fun getTextStyle(isLined: Boolean): TextStyle {
    return if (isLined) {
        TextStyle(textDecoration = TextDecoration.LineThrough)
    } else {
        TextStyle(textDecoration = TextDecoration.None)
    }
}

@Composable
fun circularCheckBox(
    checkState: Boolean,
    modifier: Modifier = Modifier,
    color: Color,
    size: Dp,
    shape: Shape,
    onChangeValue: () -> Unit
) {
    if (checkState) {
        Box(Modifier.size(size).clip(shape).background(color).then(modifier).clickable { onChangeValue() }) {
            Icon(imageVector = Icons.Default.Check, contentDescription = "", Modifier.align(Alignment.Center))
        }
    } else {
        Box(
            Modifier.size(size).border(width = size / 7, color = color, shape = shape).then(modifier)
                .clickable { onChangeValue() })
    }
}

@Composable
fun titleComponent() {
    Text(
        text = "What's up Sobhan",
        style = TextStyle(
            fontSize = 64.sp, // Set a large font size for the title
            fontWeight = FontWeight.Black, // Set heavy font weight
            color = Color.DarkGray  // Optional: Use primary color from theme

        ),
        modifier = Modifier.fillMaxWidth() // Optional: Make the text take the full width
    )
}