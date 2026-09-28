package ucb.vargasn.project.catalog.presentation.viewmodel

sealed interface CatalogEffect {

    data class ShowToast(val message: String) : CatalogEffect
}