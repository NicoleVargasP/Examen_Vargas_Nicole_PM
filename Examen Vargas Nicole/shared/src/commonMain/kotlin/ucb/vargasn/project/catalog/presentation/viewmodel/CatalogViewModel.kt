package ucb.vargasn.project.catalog.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ucb.vargasn.project.catalog.domain.usecase.GetMoviesUseCase

class CatalogViewModel(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CatalogState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<CatalogEffect>()
    val effect = _effect.asSharedFlow()

    init {
        emitEvent(CatalogEvent.OnLoadMovies)
    }

    fun emitEvent(event: CatalogEvent) {
        when (event) {

            CatalogEvent.OnLoadMovies -> {
                getMovies()
            }

            CatalogEvent.OnRetry -> {
                getMovies()
            }
        }
    }

    private fun getMovies() {
        viewModelScope.launch {

            _state.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            getMoviesUseCase.invoke().fold(

                onSuccess = { movies ->
                    _state.update {
                        it.copy(
                            movies = movies,
                            isLoading = false,
                            error = null
                        )
                    }
                },

                onFailure = { exception ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = exception.message
                        )
                    }

                    emitEffect(
                        CatalogEffect.ShowToast(
                            exception.message ?: "Error al cargar las películas"
                        )
                    )
                }
            )
        }
    }

    private fun emitEffect(effect: CatalogEffect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }
}