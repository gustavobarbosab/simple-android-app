package com.github.gustavobarbosab.simpleandroidapp.presentation.screen.list

import com.github.gustavobarbosab.simpleandroidapp.presentation.screen.list.model.ItemModel

data class ListScreenState(
    val list: List<ItemModel> = emptyList(),
    val loading: Boolean = true
)