package ucb.vargasn.project.catalog.presentation.viewmodel

sealed interface CatalogEvent {

    object OnLoadMovies : CatalogEvent

    object OnRetry : CatalogEvent
}