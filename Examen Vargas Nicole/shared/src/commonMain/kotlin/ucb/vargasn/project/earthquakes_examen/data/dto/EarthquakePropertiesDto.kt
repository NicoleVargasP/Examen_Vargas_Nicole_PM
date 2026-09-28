package ucb.vargasn.project.earthquakes_examen.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class EarthquakePropertiesDto(
    val place: String,
    val mag: Double,
    val time: Long,
    val url: String
)