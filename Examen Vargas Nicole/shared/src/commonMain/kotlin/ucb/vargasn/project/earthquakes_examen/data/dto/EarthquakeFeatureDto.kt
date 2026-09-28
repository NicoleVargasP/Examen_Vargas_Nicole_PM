package ucb.vargasn.project.earthquakes_examen.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class EarthquakeFeatureDto(
    val properties: EarthquakePropertiesDto,
    val geometry: EarthquakeGeometryDto
)