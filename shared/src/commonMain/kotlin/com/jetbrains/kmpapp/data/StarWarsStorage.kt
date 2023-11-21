package com.jetbrains.kmpapp.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface StarWarsStorage {
    suspend fun saveObjects(newObjects: List<StarWarsPlanet>)

    fun getObjectById(objectName: String): Flow<StarWarsPlanet?>

    fun getObjects(): Flow<List<StarWarsPlanet>>
}

class InMemoryStarWarsStorage : StarWarsStorage {
    private val storedObjects = kotlinx.coroutines.flow.MutableStateFlow(emptyList<StarWarsPlanet>())

    override suspend fun saveObjects(newObjects: List<StarWarsPlanet>) {
        storedObjects.value = newObjects
    }

    override fun getObjectById(objectName: String): Flow<StarWarsPlanet?> {
        return storedObjects.map { objects ->
            objects.find { it.name == objectName }
        }
    }

    override fun getObjects(): Flow<List<StarWarsPlanet>> = storedObjects
}