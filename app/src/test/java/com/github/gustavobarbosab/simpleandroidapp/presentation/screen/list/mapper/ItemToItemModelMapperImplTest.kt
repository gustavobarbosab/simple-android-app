package com.github.gustavobarbosab.simpleandroidapp.presentation.screen.list.mapper

import com.github.gustavobarbosab.simpleandroidapp.domain.entity.Item
import com.github.gustavobarbosab.simpleandroidapp.presentation.screen.list.model.ItemModel
import org.junit.Assert.assertEquals
import org.junit.Test

class ItemToItemModelMapperImplTest {

    private val subject: ItemToItemModelMapper = ItemToItemModelMapperImpl()

    @Test
    fun `mapping an empty list`() {
        // Given
        val list = emptyList<Item>()

        // When
        val result = subject.map(list)

        // Then
        val expected = emptyList<ItemModel>()
        assertEquals(expected, result)
    }

    @Test
    fun `mapping an filled list`() {
        // Given
        val list = listOf(
            Item(1, "1", "1"),
            Item(2, "2123", "2333"),
        )

        // When
        val result = subject.map(list)

        // Then
        val expected = listOf(
            ItemModel(1, "1", "1", "Item 1"),
            ItemModel(2, "2123", "2333", "Item 2"),
        )
        assertEquals(expected, result)
    }
}