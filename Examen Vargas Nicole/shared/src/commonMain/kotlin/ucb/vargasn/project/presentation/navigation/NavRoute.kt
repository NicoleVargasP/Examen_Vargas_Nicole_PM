package ucb.vargasn.project.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoute {

    @Serializable
    object Earthquake : NavRoute()

    @Serializable
    object IntegrationTest : NavRoute()

}
