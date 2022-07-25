package com.allendevbang.weathertotravel.api

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

class KtorClient {
    fun getClient():HttpClient{
        return HttpClient(Android){
            install(ContentNegotiation){
                json()
            }
        }
    }
}