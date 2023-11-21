package com.jetbrains.kmpapp

import com.jetbrains.kmpapp.data.StarWarsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinDependencies : KoinComponent {
    val starWarsRepository: StarWarsRepository by inject()
}
