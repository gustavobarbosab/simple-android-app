package com.github.gustavobarbosab.simpleandroidapp.presentation.screen.list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.gustavobarbosab.simpleandroidapp.presentation.screen.list.model.ItemModel

@Composable
fun ListScreenItem(
    item: ItemModel,
    modifier: Modifier = Modifier,
    onClick: (ItemModel) -> Unit = {},
) {
    Column(
        modifier.clickable(
            onClickLabel = item.accessibilityLabel
        ) { onClick(item) }
    ) {
        Text(item.title)
        Spacer(Modifier.padding(2.dp))
        Text(item.description)
    }
}

@Preview
@Composable
fun preview() {
    ListScreenItem(
        item = ItemModel(1, "title", "description", "Item 1"),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    )
}