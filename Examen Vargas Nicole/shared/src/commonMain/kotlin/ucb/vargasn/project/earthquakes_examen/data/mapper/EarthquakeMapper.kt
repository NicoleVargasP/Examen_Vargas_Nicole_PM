package ucb.vargasn.project.earthquakes_examen.data.mapper

import ucb.vargasn.project.earthquakes_examen.data.dto.EarthquakeFeatureDto
import ucb.vargasn.project.earthquakes_examen.domain.models.EarthquakeModel

fun EarthquakeFeatureDto.toModel(): EarthquakeModel {
    return EarthquakeModel(
        place = properties.place,
        magnitude = properties.mag,
        time = properties.time,
        url = properties.url,
        longitude = geometry.coordinates[0],
        latitude = geometry.coordinates[1],
        depth = geometry.coordinates[2]
    )
}