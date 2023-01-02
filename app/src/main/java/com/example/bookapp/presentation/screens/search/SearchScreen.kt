package com.example.bookapp.presentation.screens.search

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun SearchScreen(
    navHostController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {

    val searchQuery by searchViewModel.searchQuery
    val books = searchViewModel.searchedBooks.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchTopBar(
                text = searchQuery,
                onTextChange = {
                               searchViewModel.updateSearchQuery(query = it)
                },
                onSearchClicked = {
                                  searchViewModel.searchBooks(query = it)
                },
                onCloseClicked = {
                    navHostController.popBackStack()
                }
            )
        }
    ) {}
}