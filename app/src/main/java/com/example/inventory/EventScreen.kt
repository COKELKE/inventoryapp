package com.example.inventory

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inventory.KtorClient.getBooks
import com.example.inventory.KtorClient.getGames
import com.example.inventory.KtorClient.getGifts
import com.example.inventory.KtorClient.getMaterials
import com.example.inventory.ui.theme.InventoryTheme


data class Event(val title: String) {
    companion object {
        val data = listOf(
            Event(title = "Games"),
            Event(title = "Books"),
            Event(title = "Gifts"),
            Event(title = "Materials"),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventScreen(navController: NavHostController) {


    LazyColumn() {
        items(Event.data) { event ->
            ListItem(
                headlineText = { Text(event.title) },
                modifier = Modifier.clickable {
                    navController.navigate(event.title)
                },
                leadingContent = {
                    Icon(
                        Icons.Filled.MoreVert,
                        contentDescription = null
                    )
                }
            )
            Divider()
        }
    }
}


@Composable
fun EventNav(navController: NavHostController,snackbarHostState: SnackbarHostState) {

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



    val books = produceState(
        initialValue = listOf<Book>(),
        producer = {
            value = getBooks()
        }
    )
    val games = produceState(
        initialValue = listOf<Game>(),
        producer = {
            value = getGames()
        }
    )
    val gifts = produceState(
        initialValue = listOf<Gift>(),
        producer = {
            value = getGifts()
        }
    )
    val materials = produceState(
        initialValue = listOf<Material>(),
        producer = {
            value = getMaterials()
        }
    )

    NavHost(
        navController = navController,
        startDestination = "event",
    ) {
        composable("event") { EventScreen(navController) }
        composable("Books") { BookScreen(books.value, snackbarHostState) }
        composable("Games") { GameScreen(games.value, snackbarHostState) }
        composable("Gifts") { GiftScreen(gifts.value, snackbarHostState) }
        composable("Materials") { MaterialScreen(materials.value, snackbarHostState) }
    }
}
