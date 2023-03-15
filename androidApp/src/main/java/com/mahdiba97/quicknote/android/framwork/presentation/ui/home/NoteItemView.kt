package com.mahdiba97.quicknote.android.framwork.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahdiba97.quicknote.domain.note.Note
import com.mahdiba97.quicknote.domain.time.DateTimeUtil

@Composable
fun NoteItemView(
    note: Note,
    backgroundColor: Color,
    onNoteClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier
) {
    val formattedDate = remember(note.created) {
        DateTimeUtil.formatDateTime(note.created)
    }
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .background(backgroundColor)
            .clickable { onNoteClick() }
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = note.title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp, style = TextStyle(color = Color.White)
            )
            IconButton(onClick = onDeleteClick) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Delete note",
                    modifier = Modifier, tint = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = note.content,
            fontWeight = FontWeight.Light,
            style = TextStyle(color = Color.White)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = formattedDate,
            modifier = Modifier.align(Alignment.End), style = TextStyle(color = Color.White)
        )
    }
}