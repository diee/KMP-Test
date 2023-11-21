package com.jetbrains.kmpapp.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.jetbrains.kmpapp.R
import com.jetbrains.kmpapp.data.StarWarsPlanet
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(objectName: String, navigateBack: () -> Unit) {
    val viewModel: DetailViewModel = koinViewModel()
    val obj by viewModel.getObject(objectName).collectAsState(initial = null)

    AnimatedContent(obj != null, label = "") { objectAvailable ->
        if (objectAvailable) {
            ObjectDetails(obj!!, onBackClick = navigateBack)
        } else {
            EmptyScreenContent(Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun ObjectDetails(
    obj: StarWarsPlanet,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.White) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, stringResource(R.string.back))
                }
            }
        },
        modifier = modifier,
    ) { paddingValues ->
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {
            /*AsyncImage(
                model = obj.primaryImageSmall,
                contentDescription = obj.title,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
            )*/

            SelectionContainer {
                Column(Modifier.padding(12.dp)) {
                    Text(obj.name, style = MaterialTheme.typography.h6)
                    Spacer(Modifier.height(6.dp))
                    /*LabeledInfo(stringResource(R.string.label_artist), obj.artistDisplayName)
                    LabeledInfo(stringResource(R.string.label_date), obj.objectDate)
                    LabeledInfo(stringResource(R.string.label_dimensions), obj.dimensions)
                    LabeledInfo(stringResource(R.string.label_medium), obj.medium)
                    LabeledInfo(stringResource(R.string.label_department), obj.department)
                    LabeledInfo(stringResource(R.string.label_repository), obj.repository)
                    LabeledInfo(stringResource(R.string.label_credits), obj.creditLine)*/
                }
            }
        }
    }
}

@Composable
private fun LabeledInfo(
    label: String,
    data: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier.padding(vertical = 4.dp)) {
        Spacer(Modifier.height(6.dp))
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("$label: ")
                }
                append(data)
            }
        )
    }
}
