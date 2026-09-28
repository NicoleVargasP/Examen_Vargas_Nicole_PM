package ucb.vargasn.project.earthquakes_examen.presentation.viewmodel

import ucb.vargasn.project.earthquakes_examen.domain.models.EarthquakeModel

data class EarthquakeState(
    val earthquakes: List<EarthquakeModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)