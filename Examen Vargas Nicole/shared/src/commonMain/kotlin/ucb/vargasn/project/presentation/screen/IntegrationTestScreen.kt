package ucb.vargasn.project.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import ucb.vargasn.project.presentation.viewmodel.IntegrationTestViewModel
import ucb.vargasn.project.presentation.viewmodel.TestStatus

@Composable
fun IntegrationTestScreen(
    viewModel: IntegrationTestViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Pantalla de Diagnóstico y Pruebas",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Theme Mode Selector
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("Claro", "Oscuro", "Alto Contraste").forEach { mode ->
                Button(
                    onClick = { viewModel.setThemeMode(mode) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (state.currentThemeMode == mode) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text(mode)
                }
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(state.tests.entries.toList()) { (key, testResult) ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = testResult.name,
                                style = MaterialTheme.typography.titleMedium
                            )
                            StatusBadge(status = testResult.status)
                        }

                        Text(
                            text = testResult.message,
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Button(
                            onClick = { viewModel.runTest(key) },
                            enabled = testResult.status != TestStatus.RUNNING
                        ) {
                            if (testResult.status == TestStatus.RUNNING) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(18.dp),
                                    strokeWidth = 2.dp
                                )
                            } else {
                                Text("Ejecutar Prueba")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StatusBadge(status: TestStatus) {
    val (text, color) = when (status) {
        TestStatus.PENDING -> "Pendiente" to Color.Gray
        TestStatus.RUNNING -> "Ejecutando..." to Color.Blue
        TestStatus.SUCCESS -> "Éxito" to Color.Green
        TestStatus.ERROR -> "Error" to Color.Red
        TestStatus.BLOCKED -> "Bloqueado" to Color.Magenta
    }
    Surface(
        color = color.copy(alpha = 0.2f),
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = text,
            color = color,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelSmall
        )
    }
}
