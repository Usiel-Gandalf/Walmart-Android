package com.usi.exam.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.usi.exam.ui.detail.DetailView
import com.usi.exam.ui.detail.DetailViewModel
import com.usi.exam.ui.main.MainView
import com.usi.exam.ui.main.MainViewModel
import com.usi.exam.ui.product.CreateProductView
import com.usi.exam.ui.product.ProductView
import com.usi.exam.ui.product.ProductViewModel
import com.usi.exam.ui.security.login.LoginView
import com.usi.exam.ui.security.login.LoginViewModel
import com.usi.exam.ui.security.register.RegisterView
import com.usi.exam.ui.security.register.RegisterViewModel

@Composable
fun AuthNavGraph(
    navController: NavHostController,
    onAuthSucces: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = AuthViews.LoginView.route
    ) {

        composable(AuthViews.LoginView.route) {
            val loginViewModel: LoginViewModel = hiltViewModel()

            LoginView(loginViewModel = loginViewModel, onClicGoToRegister = {
                navController.navigate(AuthViews.RegisterView.route)
            },
                navigateToMain = {
                    onAuthSucces()
                })
        }

        composable(AuthViews.RegisterView.route) {
            val registerViewModel: RegisterViewModel = hiltViewModel()
            RegisterView(registerViewModel = registerViewModel,
                onClicGoToLogin = {
                    navController.navigate(AuthViews.LoginView.route) {
                        popUpTo(AuthViews.RegisterView.route) { inclusive = true }
                    }
                },
                onLoginNavigateToMain = {
                    onAuthSucces
                }
            )
        }
    }
}

@Composable
fun MainNavGraph(navController: NavHostController) {
    val detailViewModel: DetailViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = MainViews.MainView.route
    ) {
        composable(MainViews.MainView.route) {
            val mainViewModel: MainViewModel = hiltViewModel()
            MainView(mainViewModel = mainViewModel,
                navigateToMovieDetail = {
                    detailViewModel.setComicIdForDetail(it)
                    navController.navigate(MainViews.DetailView.route)
                },
                logout = {
                    navController.navigate(AuthViews.LoginView.route) {
                        popUpTo(AuthViews.LoginView.route) { inclusive = true }
                    }
                }
            )
        }

        composable(MainViews.DetailView.route) {
            DetailView(detailViewModel = detailViewModel,
                navToBackView = {
                    navController.navigate(MainViews.MainView.route) {
                        popUpTo(MainViews.DetailView.route) { inclusive = true }
                    }
                },
                logout = {
                    navController.navigate(AuthViews.LoginView.route) {
                        popUpTo(MainViews.DetailView.route) { inclusive = true }
                    }
                }
            )
        }

        composable(MainViews.ProductView.route) {
            val productViewModel: ProductViewModel = hiltViewModel()

            ProductView(logout = {
                navController.navigate(AuthViews.LoginView.route) {
                    popUpTo(MainViews.DetailView.route) { inclusive = true }
                }
            },
                productViewModel = productViewModel,
                onClickGotToCreateProductView = {
                    navController.navigate(MainViews.CreateProductView.route)
                }
            )
        }

        composable(MainViews.CreateProductView.route) {
            val productViewModel: ProductViewModel = hiltViewModel()
            CreateProductView(
                productViewModel = productViewModel,
                logout = {
                    navController.navigate(AuthViews.LoginView.route) {
                        popUpTo(MainViews.CreateProductView.route) { inclusive = true }
                    }
                },
                navigateToBackView = {
                    navController.navigate(MainViews.ProductView.route) {
                        popUpTo(MainViews.CreateProductView.route) { inclusive = true }
                    }
                }
            )
        }


    }
}

