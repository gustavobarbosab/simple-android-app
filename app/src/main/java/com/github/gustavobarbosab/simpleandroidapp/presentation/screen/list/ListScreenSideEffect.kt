package com.github.gustavobarbosab.simpleandroidapp.presentation.screen.list

sealed class ListScreenSideEffect {
    data class ShowFeedback(val feedback: String) : ListScreenSideEffect()
}