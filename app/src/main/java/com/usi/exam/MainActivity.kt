package com.usi.exam

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.usi.exam.ui.navigation.AuthNavGraph
import com.usi.exam.ui.navigation.MainNavGraph
import com.usi.exam.ui.navigation.MainViews
import com.usi.exam.ui.theme.ExamTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExamTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    var isAuthenticated by remember { mutableStateOf(false) }

                    if (isAuthenticated) {
                        Scaffold(bottomBar = {
                            BottomNav(navController = navController)
                        }) {it
                            MainNavGraph(navController = navController)
                        }

                    } else {
                        AuthNavGraph(navController = navController) {
                            isAuthenticated = true
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNav(
    navController: NavHostController,
) {
    var selectedItems by remember { mutableStateOf(0) }
    val list = listOf(
        MainViews.MainView,
        MainViews.ProductView
    )

    NavigationBar {
        list.forEachIndexed { index, mainViews ->
            NavigationBarItem(
                selected = selectedItems == index,
                onClick = { navController.navigate(mainViews.route)
                          selectedItems = index},
                icon = {
                    Icon(imageVector = mainViews.imageVector, contentDescription = "")
                },
                label = {
                    Text(text = mainViews.label)
                },
                alwaysShowLabel = false
            )
        }
    }
}