package ucb.vargasn.project.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoute {

    @Serializable
    object Catalog : NavRoute()
}