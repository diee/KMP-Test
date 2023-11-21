package com.jetbrains.kmpapp.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class StarWarsRepository(
    private val starWarsApi: StarWarsApi,
    private val starWarsStorage: StarWarsStorage,
) {

    private val scope = CoroutineScope(SupervisorJob())

    fun initialize() {
        scope.launch {
            refresh()
        }
    }

    suspend fun refresh() {
        starWarsStorage.saveObjects(starWarsApi.getData())
    }

    fun getObjects() = starWarsStorage.getObjects()

    fun getObjectById(objectName: String) = starWarsStorage.getObjectById(objectName)
}