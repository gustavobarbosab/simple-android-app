package com.github.gustavobarbosab.simpleandroidapp.domain.repository

interface ItemsRepository {
    suspend fun getItems(): List<Pair<String, String>>
}