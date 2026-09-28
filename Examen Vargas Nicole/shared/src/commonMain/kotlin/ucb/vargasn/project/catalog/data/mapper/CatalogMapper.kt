package ucb.vargasn.project.catalog.data.mapper

import ucb.vargasn.project.catalog.data.dto.MovieDto
import ucb.vargasn.project.catalog.domain.model.MovieModel

fun MovieDto.toModel(): MovieModel {
    return MovieModel(
        title = title,
        posterPath = posterPath
    )
}