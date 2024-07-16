package com.example.inventory

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.inventory.ui.theme.InventoryTheme
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
data class Gift(val _id: String, val title: String, val description: String,
                val category: String, val image: String,
                val location: String, val remark: String, val type: String, val remaining: String?,
                val donatedBy: String, val unitPrice: String,
                val amount: String) {
    companion object {
        val data = listOf(
            Gift("","","","","","","","","","","","",),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GiftScreen(Gifts: List<Gift>, snackbarHostState: SnackbarHostState) {
    val coroutineScope = rememberCoroutineScope()

    LazyColumn {
        items(Gifts) { Gift ->
            Card(
                onClick = { /* Do something */ },
                 //modifier = Modifier.size(width = 180.dp, height = 100.dp)
            ) {
                AsyncImage(
                    model = Gift.image,
                    contentDescription = null
                )
                Box(Modifier.fillMaxSize()) {
                    Text(Gift.title)
                }
            }
            if (Gift.remaining != "0" ) {
                Button(onClick = {
                    coroutineScope.launch {
                        val stringBody: String = KtorClient.consumeItem(Gift._id)
                        snackbarHostState.showSnackbar(stringBody)
                    }
                }) {
                    Text(text = "Consume")
                }

            } else {

                Text(text = "Not remaining")

            }
            Divider()
        }
    }
}




