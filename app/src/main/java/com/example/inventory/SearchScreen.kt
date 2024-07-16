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
import com.example.inventory.ui.theme.InventoryTheme
import kotlinx.serialization.Serializable
import kotlinx.serialization.*
import kotlinx.serialization.json.Json

val format = Json { explicitNulls = false }

@Serializable
data class Search(val _id: String, val title: String, val author: String?=null,
                  val year: String?=null, val isbn: String?=null,val description: String,
                  val category: String, val publisher: String?=null,  val image: String,
                  val location: String, val remark: String, val type: String?=null,
                  val borrower: String?=null,val remaining: String?=null, var quantity: String, ) {


    companion object {
        val data = listOf(
            Search("x","x","x","x","x","x","x","x","x",
                "x","x","x","x","x","x")
        )
        val json = format.encodeToString(data)

    }
}
// type-->??

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Searchshow(Searchs: List<Search>) {

    LazyColumn {
        items(Searchs) { search ->
            Card(
                onClick = { /* Do something */ },
                // modifier = Modifier.size(width = 180.dp, height = 100.dp)
            ) {
                AsyncImage(
                    model = search.image,
                    contentDescription = null
                )
                Box(Modifier.fillMaxSize()) {
                    Text(search.title)
                }
            }
            Divider()
        }
    }

}


@Preview(showBackground = true)
@Composable
fun SearchshowPreview() {
    InventoryTheme {
        Searchshow(Search.data)
    }
}


