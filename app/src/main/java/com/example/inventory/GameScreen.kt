package com.example.inventory

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.inventory.KtorClient.borrowItem
import com.example.inventory.KtorClient.returnItem
import com.example.inventory.ui.theme.InventoryTheme
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
data class Game(val _id: String, val title: String, val image: String,
                val description: String, val category: String, val publisher: String,
                val location: String, val remark: String, val type: String, val borrower: String, var quantity: String) {
    companion object {
        val data = listOf(
            Game("","","","","","","","","",
            "",""),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(Games: List<Game>,  snackbarHostState: SnackbarHostState) {
    val coroutineScope = rememberCoroutineScope()

    LazyColumn {
        items(Games) { game ->
            Card(
                onClick = { /* Do something */ },
                // modifier = Modifier.size(width = 180.dp, height = 100.dp)
            ) {
                AsyncImage(
                    model = game.image,
                    contentDescription = null
                )
                Box(Modifier.fillMaxSize()) {
                    Text(game.title)
                }
            }
            if (game.borrower == "none" ) {
                Button(onClick = {
                    coroutineScope.launch {
                        val stringBody: String = borrowItem(game._id)
                        snackbarHostState.showSnackbar(stringBody)
                    }
                }) {
                    Text(text = "Borrow")
                }
            } else if (game.borrower != "me") {
                Text(game.borrower)
            } else {
                Button(onClick = {
                    coroutineScope.launch {
                        val stringBody: String = returnItem(game._id)
                        snackbarHostState.showSnackbar(stringBody)
                    }
                }) {
                    Text(text = "Return")
                }
            }
            Divider()
        }
    }
}



