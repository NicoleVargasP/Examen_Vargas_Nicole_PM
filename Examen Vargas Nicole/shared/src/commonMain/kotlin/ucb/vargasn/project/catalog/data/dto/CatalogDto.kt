package ucb.vargasn.project.catalog.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CatalogDto(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)