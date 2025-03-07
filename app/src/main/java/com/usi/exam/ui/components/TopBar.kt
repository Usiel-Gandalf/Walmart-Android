package com.usi.exam.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(navToBackView: () -> Unit?, title: String, logout: () -> Unit) {
    androidx.compose.material3.TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = { navToBackView.invoke() }) {
                Row(horizontalArrangement = Arrangement.Start) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = ""
                    )
                }
            }
        },
        actions = {
            TextButton(onClick = {
                logout()
            }) {
                Text(text = "Cerrar Sesión")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithBackIcon(navToBackView: () -> Unit?, title: String) {
    androidx.compose.material3.TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = { navToBackView.invoke() }) {
                Row(horizontalArrangement = Arrangement.Start) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = ""
                    )
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithoutIcon(title: String) {
    androidx.compose.material3.TopAppBar(
        title = {
            Text(text = title)
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithoutIconAndWithAction(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior,
    logout: () -> Unit,
) {
    androidx.compose.material3.TopAppBar(
        title = {
            Text(text = title)
        },
        actions = {
            TextButton(onClick = {
                logout()
            }) {
                Text(text = "Cerrar Sesión")
            }
        },
        scrollBehavior = scrollBehavior
    )
}