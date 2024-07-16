package com.example.inventory

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.example.inventory.KtorClient.borrowItem
import com.example.inventory.KtorClient.returnItem
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
data class Book(val _id: String, val title: String, val author: String, val year: String, val isbn: String,
                val description: String, val category: String, val publisher: String,  val image: String,
                val location: String, val remark: String, val type: String, val borrower: String,) {
    companion object {
        val data = listOf(
            Book("","","","","","","","","",
            "","","",""),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookScreen(Books: List<Book>, snackbarHostState: SnackbarHostState) {
    val coroutineScope = rememberCoroutineScope()

    LazyColumn {
        items(Books) { book ->
            Card(
                onClick = { /* Do something */ },
                // modifier = Modifier.size(width = 180.dp, height = 100.dp)
            ) {
                AsyncImage(
                    model = book.image,
                    contentDescription = null
                )
                Box(Modifier.fillMaxSize()) {
                    Text(book.title, Modifier.align(Alignment.Center))
                }
            }
            if (book.borrower == "none" ) {
                Button(onClick = {
                    coroutineScope.launch {
                        val stringBody: String = borrowItem(book._id)
                        snackbarHostState.showSnackbar(stringBody)
                    }
                }) {
                    Text(text = "Borrow")
                }
            } else if (book.borrower != "me") {
                Text(book.borrower)
            } else {
                Button(onClick = {
                    coroutineScope.launch {
                        val stringBody: String = returnItem(book._id)
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


