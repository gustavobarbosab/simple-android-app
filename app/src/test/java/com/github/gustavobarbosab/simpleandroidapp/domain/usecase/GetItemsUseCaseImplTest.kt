package com.github.gustavobarbosab.simpleandroidapp.domain.usecase

import com.github.gustavobarbosab.simpleandroidapp.domain.entity.Item
import com.github.gustavobarbosab.simpleandroidapp.domain.repository.ItemsRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class GetItemsUseCaseImplTest {

    private val repository = mockk<ItemsRepository>()
    private val subject: GetItemsUseCase = GetItemsUseCaseImpl(repository)

    @Test
    fun `when asks a list should receive the expected`() = runTest {
        // Given
        coEvery { repository.getItems() } returns listOf("Title" to "Description")

        // When
        val result = subject.getItems()

        // Then
        val expected = listOf(Item(0, "Title", "Description"))
        Assert.assertEquals(expected, result)
    }
}