package com.usi.exam.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchProduct(
    searchValue: String,
    updateSearchText: (text: String) -> Unit,
    searchComic: () -> Unit,
) {
    Row(modifier = Modifier.padding(8.dp)) {
        TextField(modifier = Modifier.fillMaxWidth(),
            value = searchValue,
            onValueChange = { text ->
                updateSearchText(text)
            },
            placeholder = { Text(text = "Buscar") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        searchComic.invoke()
                    }
                )
            })
    }
}