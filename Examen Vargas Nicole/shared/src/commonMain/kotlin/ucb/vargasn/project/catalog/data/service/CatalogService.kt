package ucb.vargasn.project.catalog.data.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import ucb.vargasn.project.catalog.data.datasource.CatalogRemoteDataSource
import ucb.vargasn.project.catalog.data.dto.CatalogDto
import ucb.vargasn.project.catalog.data.mapper.toModel
import ucb.vargasn.project.catalog.domain.model.MovieModel

class CatalogService : CatalogRemoteDataSource {

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

    override suspend fun fetchData(): Result<List<MovieModel>> {
        return try {
            val response = client.get(
                "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=fa3e844ce31744388e07fa47c7c5d8c3"
            )

            val catalog = response.body<CatalogDto>()

            Result.success(
                catalog.results.map { it.toModel() }
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}