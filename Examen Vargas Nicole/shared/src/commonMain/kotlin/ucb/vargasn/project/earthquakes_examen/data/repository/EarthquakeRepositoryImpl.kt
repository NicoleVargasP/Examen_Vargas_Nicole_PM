package ucb.vargasn.project.earthquakes_examen.data.repository

import ucb.vargasn.project.earthquakes_examen.data.datasource.EarthquakeRemoteDataSource
import ucb.vargasn.project.earthquakes_examen.domain.models.EarthquakeModel
import ucb.vargasn.project.earthquakes_examen.domain.repository.EarthquakeRepository

class EarthquakeRepositoryImpl(
    val dataSource: EarthquakeRemoteDataSource
) : EarthquakeRepository {

    override suspend fun getEarthquakes(): Result<List<EarthquakeModel>> {
        return dataSource.fetchData()
    }
}