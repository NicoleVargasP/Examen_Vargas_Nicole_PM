package ucb.vargasn.project.earthquakes_examen.presentation.viewmodel

sealed interface EarthquakeEffect {

    data class ShowToast(val message: String) : EarthquakeEffect

}