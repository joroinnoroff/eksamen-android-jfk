package com.example.animee.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.animee.data.room.NewAnime

@Composable
fun NewAnimeItem(
    newAnime: NewAnime,
    editAnime: (newAnime: NewAnime) -> Unit,
    deleteAnime: () -> Unit
){
    //Bruk av key for å oppdatere komponenten med nye verdier
    key(newAnime.id) {

        var isEditing by remember { mutableStateOf(false) }
        var title by remember { mutableStateOf(newAnime.title) }
        var description by remember { mutableStateOf(newAnime.description ?: "") }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("${newAnime.id}")
            if (isEditing) {
                TextField(
                    value = title,
                    onValueChange = {title = it},
                    label = {Text("Title")},
                    modifier = Modifier.fillMaxWidth()
                )//end
                TextField(
                    value = description,
                    onValueChange = {description = it},
                    label = {Text("Description")},
                    modifier = Modifier.fillMaxWidth()

                )//end
            } else {
                Text("Title: $title")
                Text("Description: $description")
            }
            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

                if (isEditing) {
                    Button(onClick = {
                        editAnime(newAnime.copy(title = title, description = description))
                        isEditing = false

                    }) {
                        Text("Save")
                    }
                    Button(onClick = {
                        title = newAnime.title
                        description = newAnime.description ?: ""
                        isEditing = false
                    }) {
                        Text("Cancel")
                    }
                } else {
                    Button(onClick = { isEditing = true }) {
                        Text("Edit")
                    }
                    Button(onClick = { deleteAnime() }) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}



