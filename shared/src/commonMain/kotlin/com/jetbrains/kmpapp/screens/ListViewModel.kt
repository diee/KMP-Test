package com.jetbrains.kmpapp.screens

import com.jetbrains.kmpapp.data.StarWarsPlanet
import com.jetbrains.kmpapp.data.StarWarsRepository
import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow

class ListViewModel(starWarsRepository: StarWarsRepository) : KMMViewModel() {
    val objects: StateFlow<List<StarWarsPlanet>> =
        starWarsRepository.getObjects()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
