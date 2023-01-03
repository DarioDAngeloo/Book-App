package com.example.bookapp.presentation.screens.details

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun DetailsScreen(
    navHostController: NavHostController,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {
    val selectedBook = detailsViewModel.selectedBook
}