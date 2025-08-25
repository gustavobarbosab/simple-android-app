package com.github.gustavobarbosab.simpleandroidapp.presentation.screen.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.gustavobarbosab.simpleandroidapp.presentation.screen.list.components.ListScreenItem
import com.github.gustavobarbosab.simpleandroidapp.presentation.screen.list.model.ItemModel

@Composable
fun ListScreen(
    viewModel: ListScreenViewModel
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect {
            when (it) {
                is ListScreenSideEffect.ShowFeedback -> snackbarHostState.showSnackbar(
                    message = it.feedback
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.init()
    }

    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { padding ->
        ListContent(
            modifier = Modifier.padding(padding),
            list = screenState.list,
            onClick = { viewModel.onItemClick(it) }
        )
    }
}


@Composable
fun ListContent(
    modifier: Modifier = Modifier,
    list: List<ItemModel>,
    onClick: (ItemModel) -> Unit
) {
    LazyColumn(modifier.padding(vertical = 16.dp, horizontal = 8.dp)) {
        items(list, key = { it.id }) { item ->
            ListScreenItem(
                item = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                onClick = onClick
            )
        }
    }
}


@Preview(device = "spec:width=411dp,height=891dp", showSystemUi = true, showBackground = true)
@Composable
fun preview() {
    ListContent(
        list = listOf(
            ItemModel(1, "title", "description", "Item 1"),
            ItemModel(2, "title", "description", "Item 2"),
            ItemModel(3, "title", "description", "Item 3"),
            ItemModel(4, "title", "description", "Item 4"),
            ItemModel(5, "title", "description", "Item 4"),
        ),
        onClick = {}
    )
}