package ucb.vargasn.project.catalog.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel
import ucb.vargasn.project.catalog.presentation.viewmodel.CatalogEffect
import ucb.vargasn.project.catalog.presentation.viewmodel.CatalogEvent
import ucb.vargasn.project.catalog.presentation.viewmodel.CatalogViewModel

@Composable
fun CatalogScreen(
    viewModel: CatalogViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is CatalogEffect.ShowToast -> {
                    // Mostrar Toast posteriormente
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            state.error != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "No se pudieron cargar las películas"
                    )

                    Button(
                        onClick = {
                            viewModel.emitEvent(CatalogEvent.OnRetry)
                        },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text("Reintentar")
                    }
                }
            }

            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        start = 8.dp,
                        top = 16.dp,
                        end = 8.dp,
                        bottom = 16.dp
                    ),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(state.movies) { movie ->

                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(0.67f)
                                    .background(
                                        color = androidx.compose.ui.graphics.Color.LightGray
                                    ),
                                contentAlignment = Alignment.Center
                            ) {

                                if (movie.posterPath != null) {
                                    AsyncImage(
                                        model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                                        contentDescription = movie.title,
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop
                                    )
                                } else {
                                    Text(
                                        text = "Sin portada"
                                    )
                                }
                            }

                            Text(
                                text = movie.title,
                                modifier = Modifier.padding(
                                    top = 6.dp,
                                    start = 2.dp,
                                    end = 2.dp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}