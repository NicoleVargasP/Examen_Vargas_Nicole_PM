package ucb.vargasn.project.earthquakes_examen.domain.usecase

import ucb.vargasn.project.earthquakes_examen.domain.models.EarthquakeModel
import ucb.vargasn.project.earthquakes_examen.domain.repository.EarthquakeRepository

class GetEarthquakesUseCase(
    val repository: EarthquakeRepository
) {

    suspend fun invoke(): Result<List<EarthquakeModel>> {
        return repository.getEarthquakes()
    }
}