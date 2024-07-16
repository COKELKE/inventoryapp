package com.example.inventory

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inventory.ui.theme.InventoryTheme
import androidx.compose.runtime.*


@Composable
fun ItemGreeting() {
    val padding = 16.dp
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.size(padding))
        Image(
            painter = painterResource(id = R.drawable.hkbu_logo),
            contentDescription = stringResource(id = R.string.hkbu_logo),
            modifier = Modifier.size(180.dp)
        )
        Spacer(Modifier.size(padding))
        Text(text = "HKBU INVENTORY SYSTEM", fontSize = 25.sp)

    }
}

data class Itemtype(val item: String ) {
    companion object {
        val data = listOf(
            Itemtype(item = "Games"),
            Itemtype(item = "Gifts"),
            Itemtype(item = "Materials"),
            Itemtype(item = "Books"),
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemList() {
    Column {
        Itemtype.data.forEach { message ->
            ListItem(
                headlineText = { Text(message.item) },
                leadingContent = {
                    Icon(
                        Icons.Filled.MoreVert,
                        contentDescription = null
                    )
                },
            )
        }
    }
}

@Composable
fun Itemtype() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        ItemGreeting()
        ItemList()
    }
}



@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    InventoryTheme {
        Itemtype()

    }
}