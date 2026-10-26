package ucb.vargasn.project.earthquakes_examen.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import ucb.vargasn.project.earthquakes_examen.presentation.viewmodel.EarthquakeEffect
import ucb.vargasn.project.earthquakes_examen.presentation.viewmodel.EarthquakeEvent
import ucb.vargasn.project.earthquakes_examen.presentation.viewmodel.EarthquakeViewModel

@Composable
fun EarthquakeScreen(
    viewModel: EarthquakeViewModel = koinViewModel(),
    onNavigateToDiagnostic: () -> Unit = {}
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is EarthquakeEffect.ShowToast -> {
                    // Mostrar Toast posteriormente
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Terremotos USGS"
            )

            Button(onClick = onNavigateToDiagnostic) {
                Text("Diagnóstico")
            }
        }

        when {

            state.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            state.error != null -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "No se pudieron cargar los terremotos"
                    )

                    Button(
                        onClick = {
                            viewModel.emitEvent(EarthquakeEvent.OnRetry)
                        },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text("Reintentar")
                    }
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    items(state.earthquakes) { earthquake ->

                        Card(
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {

                                Text(
                                    text = "Ubicación: ${earthquake.place}"
                                )

                                Text(
                                    text = "Magnitud: ${earthquake.magnitude}"
                                )

                                Text(
                                    text = "Fecha y hora: ${earthquake.time}"
                                )

                                Text(
                                    text = "Longitud: ${earthquake.longitude}"
                                )

                                Text(
                                    text = "Latitud: ${earthquake.latitude}"
                                )

                                Text(
                                    text = "Profundidad: ${earthquake.depth} km"
                                )

                                Text(
                                    text = "Enlace: ${earthquake.url}"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}