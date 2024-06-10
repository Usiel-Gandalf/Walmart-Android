package com.usi.exam.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Place
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AuthViews(val route: String) {
    object LoginView : AuthViews(route = "LOGIN")
    object RegisterView : AuthViews(route = "REGISTER")
}

sealed class MainViews(val route: String, val imageVector: ImageVector, val label: String){
    object MainView : MainViews(route = "MAIN", imageVector = Icons.Rounded.Home, label = "MAIN")
    object DetailView : MainViews(route = "DETAIL", imageVector = Icons.Rounded.Home, label = "MAIN")
    object ProductView : MainViews(route = "PRODUCT", imageVector = Icons.Rounded.Menu, label = "PRODUCT")
    object CreateProductView : MainViews(route = "CREATE PRODUCT", imageVector = Icons.Rounded.Place, label = "CREATE PRODUCT")
}