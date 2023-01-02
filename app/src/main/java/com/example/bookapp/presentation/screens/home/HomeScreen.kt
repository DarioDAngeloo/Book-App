package com.example.bookapp.presentation.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.bookapp.navigation.Screen
import com.example.bookapp.presentation.common.ListContent
import com.example.bookapp.presentation.components.RatingWidget
import com.example.bookapp.ui.theme.LARGE_PADDING

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val allBooks = homeViewModel.getAllBooks.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopBar(onSearchClicked = {navHostController.navigate(Screen.Search.route)})
        },
        content = {
            ListContent(books = allBooks, navHostController = navHostController)
        }
    )
}