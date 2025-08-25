package com.github.gustavobarbosab.simpleandroidapp.domain.usecase

import com.github.gustavobarbosab.simpleandroidapp.domain.entity.Item
import com.github.gustavobarbosab.simpleandroidapp.domain.repository.ItemsRepository
import javax.inject.Inject

interface GetItemsUseCase {
    suspend fun getItems(): List<Item>
}

class GetItemsUseCaseImpl @Inject constructor(
    private val repository: ItemsRepository
) : GetItemsUseCase {
    override suspend fun getItems(): List<Item> = repository.getItems().mapIndexed { pos, item ->
        Item(pos, item.first, item.second)
    }
}

