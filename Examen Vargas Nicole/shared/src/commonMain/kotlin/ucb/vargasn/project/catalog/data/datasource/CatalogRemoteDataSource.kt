package ucb.vargasn.project.catalog.data.datasource

import ucb.vargasn.project.catalog.domain.model.MovieModel

interface CatalogRemoteDataSource {

    suspend fun fetchData():Result<List<MovieModel>>

}