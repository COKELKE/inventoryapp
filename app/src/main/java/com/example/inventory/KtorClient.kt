package com.example.inventory


import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable

@Serializable
data class User(val email: String, val password: String,) {
}
@Serializable
data class UserResponse(val firstname: String?=null, val last_name: String?=null,
                        val token: String?=null, val error: String?=null,) {

}
@Serializable
data class BorrowResponse(val message: String?=null, val error: String?=null){

}
@Serializable
data class ReturnResponse(val message: String?=null, val error: String?=null){

}
@Serializable
data class ConsumeResponse(val message: String?=null, val error: String?=null){

}

object KtorClient {
    private var token: String = ""
    private var message: String = ""
    private var first_name: String = ""
    private var last_name: String = ""
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json() // enable the client to perform JSON serialization
        }
        install(Logging)
        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            header("Authorization", token)
        }
//        expectSuccess = true
    }

    suspend fun getBooks(): List<Book> {
        return httpClient.get("http://comp4107.herokuapp.com/inventory?type=book").body()
    }

    suspend fun getGames(): List<Game> {
        return httpClient.get("http://comp4107.herokuapp.com/inventory?type=game").body()
    }

    suspend fun getGifts(): List<Gift> {
        return httpClient.get("http://comp4107.herokuapp.com/inventory?type=gift").body()
    }

    suspend fun getMaterials(): List<Material> {
        return httpClient.get("http://comp4107.herokuapp.com/inventory?type=material").body()
    }

    suspend fun getUsers(email: String, password: String): String {
        val response: UserResponse =
            httpClient.post("https://comp4107.herokuapp.com/user/login") {
                setBody(User(email, password))
            }.body()
        return if (response.error == null) {
            token = response.token.toString()
            first_name = response.firstname.toString()
            last_name = response.last_name.toString()
            "Logged in as ${response.firstname} ${response.last_name}"
        } else {
            response.error.toString()
        }
    }

    suspend fun getSearchs(message: String): List<Search> {
        return httpClient.get("https://comp4107.herokuapp.com/inventory?keyword=$message") {
//            setBody(message)
        }.body()
    }

    suspend fun borrowItem(_id: String): String {
        val response: BorrowResponse =
            httpClient.post("https://comp4107.herokuapp.com/user/borrow/$_id") {
              //  setBody(_id)
            }.body()
        return if (response.error ==null) {
            "Borrow success"
        }else {
            response.error.toString()
        }
    }
    suspend fun returnItem(_id: String): String {
        val response: ReturnResponse =
            httpClient.post("https://comp4107.herokuapp.com/user/return/$_id") {
                //  setBody(_id)
            }.body()
        return if (response.error ==null) {
            "Return success"
        }else {
            response.error.toString()
        }
    }
    suspend fun consumeItem(_id: String): String {
        val response: ConsumeResponse =
            httpClient.post("https://comp4107.herokuapp.com/user/consume/$_id") {
                //  setBody(_id)
            }.body()
        return if (response.error ==null) {
            "Consume success"
        }else {
            response.error.toString()
        }
    }




}




