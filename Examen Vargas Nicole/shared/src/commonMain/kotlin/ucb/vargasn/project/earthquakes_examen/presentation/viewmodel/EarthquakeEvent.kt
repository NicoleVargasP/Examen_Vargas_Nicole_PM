package ucb.vargasn.project.earthquakes_examen.presentation.viewmodel

sealed interface EarthquakeEvent {

    object OnLoadEarthquakes : EarthquakeEvent

    object OnRetry : EarthquakeEvent

}