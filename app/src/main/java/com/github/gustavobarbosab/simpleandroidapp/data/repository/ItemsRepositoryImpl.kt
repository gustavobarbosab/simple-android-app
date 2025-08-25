package com.github.gustavobarbosab.simpleandroidapp.data.repository

import com.github.gustavobarbosab.simpleandroidapp.domain.repository.ItemsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor() : ItemsRepository {
    // Here I'm not doing anything special, I'm only changing the dispatcher to show
    // a good practice for long tasks
    override suspend fun getItems(): List<Pair<String, String>> = withContext(Dispatchers.Default) {
        // Here I could move this logic to a datasource
        val list = mutableListOf<Pair<String, String>>()
        repeat(10) {
            list.add("Title $it" to "Description $it")
        }
        return@withContext list
    }
}