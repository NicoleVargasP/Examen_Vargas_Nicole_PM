package ucb.vargasn.project.catalog.data.repository

import ucb.vargasn.project.catalog.data.datasource.CatalogRemoteDataSource
import ucb.vargasn.project.catalog.domain.model.MovieModel
import ucb.vargasn.project.catalog.domain.repository.CatalogRepository

class CatalogRepositoryImpl (val dataSource: CatalogRemoteDataSource): CatalogRepository {
    override  suspend fun getMovies(): Result <List<MovieModel>>{
        return dataSource.fetchData()
    }
}