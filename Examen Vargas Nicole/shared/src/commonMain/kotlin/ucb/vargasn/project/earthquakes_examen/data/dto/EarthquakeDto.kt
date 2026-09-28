package ucb.vargasn.project.earthquakes_examen.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class EarthquakeDto(
    val features: List<EarthquakeFeatureDto>
)