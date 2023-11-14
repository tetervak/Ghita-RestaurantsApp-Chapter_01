package ca.tetervak.restaurantapp.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.tetervak.restaurantapp.domain.GetRestaurantsUseCase
import ca.tetervak.restaurantapp.domain.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    getRestaurantsUseCase: GetRestaurantsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    val listUiState: StateFlow<ListUiState> =
        getRestaurantsUseCase().map { ListUiState.Success(it) }.catch { errorHandler }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ListUiState.Loading
            )

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
    }

    fun toggleFavorite(id: Int, currentIsFavorite: Boolean) = viewModelScope.launch(errorHandler) {
        toggleFavoriteUseCase(id, currentIsFavorite)
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}