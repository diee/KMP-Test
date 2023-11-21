package com.jetbrains.kmpapp.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface StarWarsApi {
    suspend fun getData(): List<StarWarsPlanet>
}

class KtorStarWarsApi(private val client: HttpClient) : StarWarsApi {
    companion object {
        private const val API_URL =
            "https://swapi.dev/api/planets/"
    }

    override suspend fun getData(): List<StarWarsPlanet> {
        return try {
            val result: StarWarsPlanetList = client.get(API_URL).body()
            result.results
        } catch (e: Exception) {
            if (e is kotlin.coroutines.cancellation.CancellationException) throw e
            e.printStackTrace()

            emptyList()
        }
    }
}