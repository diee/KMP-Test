package com.jetbrains.kmpapp.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jetbrains.kmpapp.data.StarWarsPlanet
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListScreen(navigateToDetails: (objectId: String) -> Unit) {
    val viewModel: ListViewModel = koinViewModel()
    val objects by viewModel.objects.collectAsState()

    AnimatedContent(objects.isNotEmpty(), label = "") { objectsAvailable ->
        if (objectsAvailable) {
            ObjectGrid(
                objects = objects,
                onObjectClick = navigateToDetails,
            )
        } else {
             EmptyScreenContent(Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun ObjectGrid(
    objects: List<StarWarsPlanet>,
    onObjectClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(120.dp),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(objects, key = { it.name }) { obj ->
            ObjectFrame(
                obj = obj,
                onClick = { onObjectClick(obj.name) },
            )
        }
    }
}

@Composable
private fun ObjectFrame(
    obj: StarWarsPlanet,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        /*AsyncImage(
            model = obj.primaryImageSmall,
            contentDescription = obj.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(Color.LightGray),
        )*/

        Spacer(Modifier.height(2.dp))

        Text(obj.name, style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold))
        Text(obj.rotation_period, style = MaterialTheme.typography.body2)
        Text(obj.orbital_period, style = MaterialTheme.typography.caption)
    }
}
