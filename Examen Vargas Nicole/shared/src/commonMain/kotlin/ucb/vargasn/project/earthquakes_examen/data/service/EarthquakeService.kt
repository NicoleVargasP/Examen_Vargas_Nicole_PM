package ucb.vargasn.project.earthquakes_examen.data.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import ucb.vargasn.project.earthquakes_examen.data.datasource.EarthquakeRemoteDataSource
import ucb.vargasn.project.earthquakes_examen.data.dto.EarthquakeDto
import ucb.vargasn.project.earthquakes_examen.data.mapper.toModel
import ucb.vargasn.project.earthquakes_examen.domain.models.EarthquakeModel

class EarthquakeService : EarthquakeRemoteDataSource {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    override suspend fun fetchData(): Result<List<EarthquakeModel>> {
        return try {

            val response = client.get(
                "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&minmagnitude=5&limit=3"
            )

            val earthquakes = response.body<EarthquakeDto>()

            Result.success(
                earthquakes.features.map { it.toModel() }
            )

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}