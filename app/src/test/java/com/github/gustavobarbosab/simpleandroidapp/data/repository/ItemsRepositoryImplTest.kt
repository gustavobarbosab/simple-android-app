package com.github.gustavobarbosab.simpleandroidapp.data.repository

import com.github.gustavobarbosab.simpleandroidapp.domain.repository.ItemsRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class ItemsRepositoryImplTest {

    private val subject: ItemsRepository = ItemsRepositoryImpl()

    @Test
    fun `when asks a list, should return the expected`() = runTest {
        // When
        val items = subject.getItems()

        // Then
        val expected = listOf(
            "Title 0" to "Description 0",
            "Title 1" to "Description 1",
            "Title 2" to "Description 2",
            "Title 3" to "Description 3",
            "Title 4" to "Description 4",
            "Title 5" to "Description 5",
            "Title 6" to "Description 6",
            "Title 7" to "Description 7",
            "Title 8" to "Description 8",
            "Title 9" to "Description 9",
        )

        Assert.assertEquals(expected, items)
    }
}