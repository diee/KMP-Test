package com.jetbrains.kmpapp.di

import co.touchlab.skie.configuration.annotations.DefaultArgumentInterop
import com.jetbrains.kmpapp.data.InMemoryStarWarsStorage
import com.jetbrains.kmpapp.data.KtorStarWarsApi
import com.jetbrains.kmpapp.data.StarWarsApi
import com.jetbrains.kmpapp.data.StarWarsRepository
import com.jetbrains.kmpapp.data.StarWarsStorage
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

val dataModule = module {
    single {
        val json = Json { ignoreUnknownKeys = true }
        HttpClient {
            install(ContentNegotiation) {
                // TODO Fix API so it serves application/json
                json(json, contentType = ContentType.Any)
            }
        }
    }

    single<StarWarsApi> { KtorStarWarsApi(get()) }
    single<StarWarsStorage> { InMemoryStarWarsStorage() }
    single {
        StarWarsRepository(get(), get()).apply {
            initialize()
        }
    }
}

@DefaultArgumentInterop.Enabled
fun initKoin(modules: List<Module> = emptyList()) {
    startKoin {
        modules(
            dataModule,
            *modules.toTypedArray(),
        )
    }
}
