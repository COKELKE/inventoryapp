package com.example.inventory

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inventory.KtorClient.getSearchs
import com.example.inventory.ui.theme.InventoryTheme

import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable


@Composable
fun SearchScreen(navController: NavHostController, snackbarHostState: SnackbarHostState) {
    val padding = 16.dp

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchNav(navController: NavHostController, snackbarHostState: SnackbarHostState) {
    val padding = 16.dp
    var message by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

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

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.size(padding))
        TextField(
            maxLines = 1,
            value = message,
            onValueChange = { message = it }
        )
        Spacer(Modifier.size(padding))

        Button(onClick = {
            coroutineScope.launch {
                val ArrayList: List<Search> = getSearchs(message)
                snackbarHostState.showSnackbar(ArrayList.toString())
            }
        }) {
            Text(text = "Search")
        }
    }



    val search = produceState(
        initialValue = listOf<Search>(),
        producer = {
            value = getSearchs(message)
        }
    )

    NavHost(
        navController = navController,
        startDestination = "event",
    ) {
        composable("event") { SearchScreen(navController, SnackbarHostState()) }

        composable("Search") { Searchshow(search.value) }
    }
}




@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    InventoryTheme {
        var snackbarHostState = remember { SnackbarHostState()}
        //SearchScreen(snackbarHostState)
        SearchNav(rememberNavController(), snackbarHostState)

    }
}

