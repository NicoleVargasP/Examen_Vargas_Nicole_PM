package ucb.vargasn.project.earthquakes_examen.domain.repository

import ucb.vargasn.project.earthquakes_examen.domain.models.EarthquakeModel

interface EarthquakeRepository {

    suspend fun getEarthquakes(): Result<List<EarthquakeModel>>

}
