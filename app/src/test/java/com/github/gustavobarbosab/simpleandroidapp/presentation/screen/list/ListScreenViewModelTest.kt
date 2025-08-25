package com.github.gustavobarbosab.simpleandroidapp.presentation.screen.list

import com.github.gustavobarbosab.simpleandroidapp.TestCoroutineRule
import com.github.gustavobarbosab.simpleandroidapp.domain.entity.Item
import com.github.gustavobarbosab.simpleandroidapp.domain.usecase.GetItemsUseCase
import com.github.gustavobarbosab.simpleandroidapp.presentation.screen.list.mapper.ItemToItemModelMapperImpl
import com.github.gustavobarbosab.simpleandroidapp.presentation.screen.list.model.ItemModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class ListScreenViewModelTest {

    @get:Rule
    val testCoroutine = TestCoroutineRule()

    private val getItemsUseCase = mockk<GetItemsUseCase>()
    private val mapper = mockk<ItemToItemModelMapperImpl>()
    val subject = ListScreenViewModel(getItemsUseCase, mapper)

    @Test
    fun `given the user opens the screen, should emit the new state with the list`() =
        runTest {
            // Given
            coEvery { getItemsUseCase.getItems() } returns listOf(
                Item(1, "Title", "Description")
            )

            every { mapper.map(any()) } returns listOf(
                ItemModel(
                    id = 0,
                    title = "Title",
                    description = "Description",
                    accessibilityLabel = "Accessibility"
                )
            )

            // When
            subject.init()
            advanceUntilIdle()

            // Then
            val expected = ListScreenState(
                list = listOf(
                    ItemModel(
                        id = 0,
                        title = "Title",
                        description = "Description",
                        accessibilityLabel = "Accessibility"
                    )
                ),
                loading = false
            )
            Assert.assertEquals(expected, subject.screenState.first())
        }


    @Test
    fun `given the user clicks on a item, should emit ShowFeedback side effect`() =
        runTest {
            // Given
            val item = ItemModel(1, "Title", "Description", "Accessibility")

            // When
            subject.onItemClick(item)

            // Then
            val expected = ListScreenSideEffect.ShowFeedback("Title")
            Assert.assertEquals(expected, subject.sideEffect.firstOrNull())
        }
}