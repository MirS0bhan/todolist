package ir.mirsobhan.apps.todolist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DisableNonLinearFontScalingInCompose
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

private var textFC  = TextFieldColors(
    unfocusedContainerColor = Color.White,
    focusedTextColor = Color.Black,
    unfocusedTextColor = Color.DarkGray,
    disabledTextColor = Color.LightGray,
    errorTextColor = Color.White,
    focusedContainerColor = Color.White,
    disabledContainerColor = Color.White,
    errorContainerColor = Color.Red,
    cursorColor = Color.Blue,
    errorCursorColor = Color.Gray,
    textSelectionColors = TextSelectionColors(Color.Blue, Color.White),
    focusedIndicatorColor = Color.White,
    unfocusedIndicatorColor = Color.White,
    disabledIndicatorColor = Color.White,
    errorIndicatorColor = Color.White,
    focusedLeadingIconColor = Color.White,
    unfocusedLeadingIconColor = Color.White,
    disabledLeadingIconColor = Color.White,
    errorLeadingIconColor = Color.White,
    focusedTrailingIconColor = Color.White,
    unfocusedTrailingIconColor = Color.White,
    disabledTrailingIconColor = Color.White,
    errorTrailingIconColor = Color.White,
    focusedLabelColor = Color.White,
    unfocusedLabelColor = Color.White,
    disabledLabelColor = Color.White,
    errorLabelColor = Color.White,
    focusedPlaceholderColor = Color.LightGray,
    unfocusedPlaceholderColor = Color.Gray,
    disabledPlaceholderColor = Color.White,
    errorPlaceholderColor = Color.White,
    focusedSupportingTextColor = Color.White,
    unfocusedSupportingTextColor = Color.White,
    disabledSupportingTextColor = Color.White,
    errorSupportingTextColor = Color.White,
    focusedPrefixColor = Color.White,
    unfocusedPrefixColor = Color.White,
    disabledPrefixColor = Color.White,
    errorPrefixColor = Color.White,
    focusedSuffixColor = Color.White,
    unfocusedSuffixColor = Color.White,
    disabledSuffixColor = Color.White,
    errorSuffixColor = Color.White
)


@Composable
fun addDialog(onDismissRequest: () -> Unit, onDone: (Task) -> Unit) {
    var taskTitle by remember { mutableStateOf("") }

    Dialog(onDismissRequest, DialogProperties()) {
        Box(Modifier.fillMaxSize().fillMaxWidth().background(Color.White).padding(25.dp)) {
            Button(modifier = Modifier.align(Alignment.TopEnd), onClick = onDismissRequest) {
                Icon(imageVector = Icons.Filled.Close, contentDescription = "")
            }

            Column(Modifier.align(Alignment.Center)){

                TextField(taskTitle, placeholder = { Text("test")}, onValueChange = {
                    taskTitle = it
                }, singleLine = true, colors = textFC)

            }

            Button({ if(taskTitle.isNotBlank()) {onDone(Task(taskTitle))} else { onDismissRequest()}}, Modifier.align(Alignment.BottomEnd), colors = ButtonColors(Color.Blue, Color.White, Color.White, Color.White)) {
                Row {
                    Text("add task")
                    Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "")
                }
            }
        }
    }
}