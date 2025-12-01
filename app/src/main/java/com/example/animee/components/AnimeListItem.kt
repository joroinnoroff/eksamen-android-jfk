package com.example.animee.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.animee.data.retrofit.AnimeApi

@Composable
fun AnimeListItem(
    anime: AnimeApi,
    showDetails: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp)
    ){
        Box(
            modifier = Modifier.padding(16.dp)
                .fillMaxSize()
        ) {
            Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(" ${anime.japaneseTitle}",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold)
                AsyncImage(
                    model = anime.images.webp.image,
                    contentDescription = anime.englishTitle,
                    modifier = Modifier.fillMaxSize()
                        .padding(12.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
                Button(
                    showDetails
                ) {
                    Text("Vis detaljer")
                }
            }
        }
    }
}