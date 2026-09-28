package ucb.vargasn.project.earthquakes_examen.data.datasource

import ucb.vargasn.project.earthquakes_examen.domain.models.EarthquakeModel

interface EarthquakeRemoteDataSource {

    suspend fun fetchData(): Result<List<EarthquakeModel>>

}