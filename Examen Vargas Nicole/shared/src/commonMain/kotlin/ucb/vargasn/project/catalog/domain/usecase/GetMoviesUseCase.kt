package ucb.vargasn.project.catalog.domain.usecase

import ucb.vargasn.project.catalog.domain.model.MovieModel
import ucb.vargasn.project.catalog.domain.repository.CatalogRepository

class GetMoviesUseCase (val repository: CatalogRepository){
    suspend fun invoke(): Result<List<MovieModel>>{
        return repository.getMovies()
    }
}