package com.example.inventory

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.inventory.ui.theme.InventoryTheme
import kotlinx.coroutines.launch
@Composable
fun UserScreen(snackBarHostState: SnackbarHostState) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Userlogin(snackBarHostState)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Userlogin(snackbarHostState: SnackbarHostState) {
    val padding = 16.dp
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf( "") }
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
    Column() {
        Spacer(Modifier.size(padding))
        Text("Email Address：")
        TextField(
            maxLines = 1,
            value = email,
            onValueChange = { email = it }
        )
        Spacer(Modifier.size(padding))
        Text("Password：")
        TextField(
            maxLines = 1,
            value = password,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(Modifier.size(padding))
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            coroutineScope.launch {
                val stringBody: String = KtorClient.getUsers(email, password)
                snackbarHostState.showSnackbar(stringBody)
            }
        }) {
            Text(text = "Login")
        }
    }
}

//
//
//@Preview(showBackground = true)
//@Composable
//fun UserPreview() {
//    InventoryTheme {
//        var snackbarHostState = remember { SnackbarHostState()}
//
//    }
//}

