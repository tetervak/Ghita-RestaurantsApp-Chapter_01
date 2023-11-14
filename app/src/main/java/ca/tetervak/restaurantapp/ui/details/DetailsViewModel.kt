package ca.tetervak.restaurantapp.ui.details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.tetervak.restaurantapp.data.repository.RestaurantRepository
import ca.tetervak.restaurantapp.domain.Restaurant
import ca.tetervak.restaurantapp.ui.navigation.DetailsDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    repository: RestaurantRepository, stateHandle: SavedStateHandle
) : ViewModel() {

    private val _detailsUiState: MutableStateFlow<DetailsUiState> =
        MutableStateFlow(DetailsUiState.Loading)
    val detailsUiState: StateFlow<DetailsUiState> = _detailsUiState

    init {
        val id: Int = stateHandle.get<Int>(DetailsDestination.restaurantIdArg)!!
        viewModelScope.launch {
            val restaurant: Restaurant? = repository.getRestaurantById(id)
            if(restaurant != null){
                _detailsUiState.update {
                    DetailsUiState.Success(restaurant = restaurant)
                }
            } else {
                Log.e("DetailsViewModel", "data for id=$id is not found")
            }
        }
    }
}