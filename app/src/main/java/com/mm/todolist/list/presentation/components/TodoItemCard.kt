package com.mm.todolist.list.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mm.todolist.list.domain.TodoUI
import com.mm.todolist.ui.theme.TodoListTheme

@Composable
fun TodoItemCard(
    item: TodoUI,
    modifier: Modifier = Modifier,
    onClick: (TodoUI) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick(item)
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = if (item.completed) Color.Gray else Color.Black,
                        fontWeight = FontWeight.Medium
                    ),
                    fontSize = 16.sp
                )
                Text(
                    text = item.status,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = if (item.completed) Color(0xFF00BCD4) else Color(0xFFF44336),
                        fontWeight = FontWeight.Medium
                    ),
                    fontSize = 14.sp
                )
            }

            AnimatedVisibility(
                visible = true,
                enter = fadeIn(animationSpec = tween(300)),
                exit = fadeOut(animationSpec = tween(300))
            ) {
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(
                            if (item.completed) Color(0xFFE0F7FA)
                            else Color(0xFFFBE9E7)
                        )
                ) {
                    Icon(
                        imageVector = if (item.completed) Icons.Filled.Check else Icons.Filled.Close,
                        contentDescription = item.status,
                        tint = if (item.completed) Color(0xFF00BCD4) else Color(0xFFF44336),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewTodoItemCard(modifier: Modifier = Modifier) {
    TodoListTheme {
        TodoItemCard(
            item = TodoUI(1, "Test 1", true, "Completed")
        ) { }
    }

}