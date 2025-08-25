package com.github.gustavobarbosab.simpleandroidapp.presentation.screen.list.mapper

import com.github.gustavobarbosab.simpleandroidapp.domain.entity.Item
import com.github.gustavobarbosab.simpleandroidapp.presentation.screen.list.model.ItemModel
import javax.inject.Inject

interface ItemToItemModelMapper {
    fun map(items: List<Item>): List<ItemModel>
}

class ItemToItemModelMapperImpl @Inject constructor() : ItemToItemModelMapper {
    override fun map(items: List<Item>): List<ItemModel> = items.map {
        ItemModel(
            it.id,
            it.title,
            it.description,
            "Item ${it.id}" // I could change this to use a strings provider...
        )
    }
}