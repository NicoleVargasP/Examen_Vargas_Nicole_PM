package ucb.vargasn.project.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoute {

    @Serializable
    object Welcome : NavRoute()

    @Serializable
    object Home : NavRoute()

    @Serializable
    data class LabDetail(val labId: String) : NavRoute()

}
