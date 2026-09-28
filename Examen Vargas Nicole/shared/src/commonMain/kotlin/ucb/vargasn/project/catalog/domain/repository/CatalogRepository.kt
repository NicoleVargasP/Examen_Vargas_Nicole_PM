package ucb.vargasn.project.catalog.domain.repository

import ucb.vargasn.project.catalog.domain.model.MovieModel

interface CatalogRepository {

    suspend fun getMovies(): Result<List<MovieModel>>

}