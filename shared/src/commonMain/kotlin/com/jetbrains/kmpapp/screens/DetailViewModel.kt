package com.jetbrains.kmpapp.screens

import com.jetbrains.kmpapp.data.StarWarsPlanet
import com.jetbrains.kmpapp.data.StarWarsRepository
import com.rickclephas.kmm.viewmodel.KMMViewModel
import kotlinx.coroutines.flow.Flow

class DetailViewModel(private val starWarsRepository: StarWarsRepository) : KMMViewModel() {
    fun getObject(objectString: String): Flow<StarWarsPlanet?> =
        starWarsRepository.getObjectById(objectString)
}
