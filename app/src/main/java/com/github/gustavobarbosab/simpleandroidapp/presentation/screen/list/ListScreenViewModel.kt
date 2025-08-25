package com.github.gustavobarbosab.simpleandroidapp.presentation.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.gustavobarbosab.simpleandroidapp.domain.usecase.GetItemsUseCase
import com.github.gustavobarbosab.simpleandroidapp.domain.usecase.GetItemsUseCaseImpl
import com.github.gustavobarbosab.simpleandroidapp.presentation.screen.list.mapper.ItemToItemModelMapper
import com.github.gustavobarbosab.simpleandroidapp.presentation.screen.list.mapper.ItemToItemModelMapperImpl
import com.github.gustavobarbosab.simpleandroidapp.presentation.screen.list.model.ItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase,
    private val itemsMapper: ItemToItemModelMapper
) : ViewModel() {

    private val _screenState = MutableStateFlow(ListScreenState())
    val screenState = _screenState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<ListScreenSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    fun init() {
        viewModelScope.launch {
            _screenState.update { it.copy(loading = true) }
            val items = getItemsUseCase.getItems()
            val itemsModel = itemsMapper.map(items)
            _screenState.update { it.copy(loading = false, list = itemsModel) }
        }
    }

    fun onItemClick(item: ItemModel) {
        viewModelScope.launch {
            _sideEffect.emit(ListScreenSideEffect.ShowFeedback(item.title))
        }
    }
}