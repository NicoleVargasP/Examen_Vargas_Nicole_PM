package ucb.vargasn.project.catalog.presentation.viewmodel

import ucb.vargasn.project.catalog.domain.model.MovieModel

data class CatalogState(
    val movies: List<MovieModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)