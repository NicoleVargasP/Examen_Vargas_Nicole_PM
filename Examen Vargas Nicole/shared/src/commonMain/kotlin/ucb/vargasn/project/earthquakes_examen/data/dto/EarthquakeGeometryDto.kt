package ucb.vargasn.project.earthquakes_examen.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class EarthquakeGeometryDto(
    val coordinates: List<Double>
)