package ucb.vargasn.project.earthquakes_examen.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ucb.vargasn.project.earthquakes_examen.domain.usecase.GetEarthquakesUseCase

class EarthquakeViewModel(
    private val getEarthquakesUseCase: GetEarthquakesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(EarthquakeState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<EarthquakeEffect>()
    val effect = _effect.asSharedFlow()

    init {
        emitEvent(EarthquakeEvent.OnLoadEarthquakes)
    }

    fun emitEvent(event: EarthquakeEvent) {
        when (event) {

            EarthquakeEvent.OnLoadEarthquakes -> {
                getEarthquakes()
            }

            EarthquakeEvent.OnRetry -> {
                getEarthquakes()
            }
        }
    }

    private fun getEarthquakes() {

        viewModelScope.launch {

            _state.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            getEarthquakesUseCase.invoke().fold(

                onSuccess = { earthquakes ->

                    _state.update {
                        it.copy(
                            earthquakes = earthquakes,
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
                        EarthquakeEffect.ShowToast(
                            exception.message ?: "Error al cargar los terremotos"
                        )
                    )
                }
            )
        }
    }

    private fun emitEffect(effect: EarthquakeEffect) {

        viewModelScope.launch {
            _effect.emit(effect)
        }
    }
}