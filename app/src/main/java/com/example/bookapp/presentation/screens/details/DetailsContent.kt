package com.example.bookapp.presentation.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookapp.domain.model.Book
import com.example.bookapp.ui.theme.*
import com.example.bookapp.R
import com.example.bookapp.presentation.components.InfoBox
import com.example.bookapp.presentation.components.OrderedList
import com.example.bookapp.util.Constants.ABOUT_MAX_LINES_DETAILSCREEN
import com.example.bookapp.util.Constants.BASE_URL


@ExperimentalMaterialApi
@Composable
fun DetailsContent(
    navHostController: NavHostController,
    selectedBook: Book?
) {
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            selectedBook?.let { BottomSheetContent(selectedBook = it) }
        },
        content = {
            selectedBook?.let { book ->
                BackgroundContent(
                    bookImg = book.image,
                    onCloseClicked = {
                        navHostController.popBackStack()
                    }
                )
            }
        }
    )


}

@Composable
fun BottomSheetContent(
    selectedBook: Book,
    infoBoxIconColor: Color = MaterialTheme.colors.buttonBackgroundColor,
    sheetBackgroundColor: Color = MaterialTheme.colors.welcomeScreenBackgroundColor,
    contentColor: Color = MaterialTheme.colors.titleColor
) {
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(all = LARGE_PADDING)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(ICONS_INFOBOX_SIZE)
                    .weight(2f),
                painter = painterResource(id = R.drawable.vector),
                contentDescription = stringResource(
                    R.string.logo_icon_detailss
                ),
                tint = contentColor
            )
            Text(
                modifier = Modifier
                    .weight(8f),
                text = selectedBook.name,
                color = contentColor,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING),
            horizontalArrangement = Arrangement.spacedBy(EXTRA_LARGE_PADDING)
        ) {
            InfoBox(
                icon = painterResource(id = R.drawable.calendar),
                iconColor = infoBoxIconColor,
                bigText = selectedBook.month,
                smallText = stringResource(R.string.month_details_placeholder),
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.day),
                iconColor = infoBoxIconColor,
                bigText = selectedBook.day,
                smallText = stringResource(R.string.day_placeholder_details),
                textColor = contentColor
            )
        }

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(R.string.about_detail),
            color = contentColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .padding(bottom = MEDIUM_PADDING),
            text = selectedBook.about,
            color = contentColor,
            fontSize = MaterialTheme.typography.body1.fontSize,
            maxLines = ABOUT_MAX_LINES_DETAILSCREEN
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OrderedList(
                title = stringResource(R.string.tags_placeholder_detail),
                items = selectedBook.tags,
                textColor = contentColor
            )
        }

    }
}


@Composable
fun BackgroundContent(
    bookImg: String,
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colors.welcomeScreenBackgroundColor,
    onCloseClicked: () -> Unit
) {

    val imageUrl = "$BASE_URL${bookImg}"


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction)
                .align(Alignment.TopStart),
            model = ImageRequest.Builder(LocalContext.current)
                .data(data = imageUrl)
                .error(drawableResId = R.drawable.placeholder)
                .build(), contentDescription = stringResource(R.string.book_image),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier.padding(all = SMALL_PADDING),
                onClick = { onCloseClicked() }) {
                Icon(
                    modifier = Modifier.size(ICONS_INFOBOX_SIZE),
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.close_icon),
                    tint = Color.White
                )
            }
        }
    }
}


@Preview
@Composable
fun Bottom() {
    BottomSheetContent(
        selectedBook = Book(
            id = 1,
            name = "flow",
            image = "",
            about = "nihon 2023",
            rating = 2.0,
            month = "july",
            day = "23",
            tags = listOf("osaaka", "kyoto", "oamori")
        )
    )
}