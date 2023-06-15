package com.codingtroops.restaurantsapp.screens.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.codingtroops.restaurantsapp.R
import com.codingtroops.restaurantsapp.model.Restaurant
import com.codingtroops.restaurantsapp.screens.common.RestaurantDetails
import com.codingtroops.restaurantsapp.screens.common.RestaurantIcon

@Composable
fun ListScreen(
    viewModel: ListViewModel,
    onItemClick: (id: Int) -> Unit
) {
    val listUiState: ListUiState = viewModel.listUiState.collectAsState().value
    if(listUiState is ListUiState.Success){
        val restaurants: List<Restaurant> = listUiState.restaurants
        LazyColumn(
            contentPadding = PaddingValues(
                vertical = 8.dp,
                horizontal = 8.dp
            )
        ) {
            items(restaurants) { restaurant ->
                RestaurantItem(
                    item = restaurant,
                    onFavoriteClick = { viewModel.toggleFavorite(restaurant.id) },
                    onItemClick = onItemClick
                )
            }
        }
    }
}

@Composable
fun RestaurantItem(
    item: Restaurant,
    onFavoriteClick: (id: Int) -> Unit,
    onItemClick: (id: Int) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .padding(8.dp)
            .clickable { onItemClick(item.id) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            RestaurantIcon(
                icon = Icons.Filled.Place,
                modifier = Modifier.weight(0.15f)
            )
            RestaurantDetails(
                title = item.title,
                description = item.description,
                modifier = Modifier.weight(0.7f)
            )
            SelectionIcon(
                icon = if (item.isFavorite) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Filled.FavoriteBorder
                },
                onClick = { onFavoriteClick(item.id) },
                modifier = Modifier.weight(0.15f)
            )
        }
    }
}

@Composable
private fun SelectionIcon(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Image(
        imageVector = icon,
        contentDescription = stringResource(R.string.selection_icon),
        modifier = modifier
            .padding(8.dp)
            .clickable { onClick() })
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    MainTheme {
//        ListScreen(onItemClick = {})
//    }
//}