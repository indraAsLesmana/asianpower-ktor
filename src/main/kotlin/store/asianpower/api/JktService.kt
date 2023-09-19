package store.asianpower.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.request.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

suspend fun fetchSearchResults(query: String): List<Product> {
    val client = HttpClient(CIO) {
        install(Auth) {
            basic {
                credentials {
                    BasicAuthCredentials(username = "indra", password = "indra")
                }
            }
        }
        install(ContentNegotiation) {
            json()
        }
    }

    try {
        // Make the API request
        val url = "http://api.asianpower.store/api/search?key=$query"
        val response = client.get(url).body<List<Product>>()

        // Return the API response as a String
        return response
    } finally {
        // Close the HttpClient when done
        client.close()
    }
}
