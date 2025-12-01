package com.example.animee.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.animee.data.room.NewAnime

@Composable
fun NewAnimeListItem(
    newAnime: NewAnime,
    editAnime: () -> Unit
) {

        OutlinedButton(
            editAnime,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),

            contentPadding = PaddingValues(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()



            ) {
                Row() {
                    Text("Id: ${newAnime.id}")
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )

                }
                Text("Title: ${newAnime.title}")

            }
        }


}