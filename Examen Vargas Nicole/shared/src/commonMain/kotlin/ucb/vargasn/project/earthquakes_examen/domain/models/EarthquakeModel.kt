package ucb.vargasn.project.earthquakes_examen.domain.models

data class EarthquakeModel(
    val place: String,
    val magnitude: Double,
    val time: Long,
    val url: String,
    val longitude: Double,
    val latitude: Double,
    val depth: Double
)