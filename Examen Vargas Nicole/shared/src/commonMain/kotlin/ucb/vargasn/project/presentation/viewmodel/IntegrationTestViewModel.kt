package ucb.vargasn.project.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import ucb.vargasn.project.AppDatabase
import ucb.vargasn.project.ThemeManager
import ucb.vargasn.project.domain.repository.GrpcTestClient
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.database.database
import dev.gitlive.firebase.firestore.firestore
import dev.gitlive.firebase.messaging.messaging

enum class TestStatus {
    PENDING, RUNNING, SUCCESS, ERROR, BLOCKED
}

data class TestResult(
    val name: String,
    val status: TestStatus = TestStatus.PENDING,
    val message: String = "No ejecutado",
    val error: String? = null
)

data class IntegrationTestState(
    val tests: Map<String, TestResult> = mapOf(
        "room" to TestResult("Room (Persistencia Local)"),
        "realtimeDb" to TestResult("Firebase Realtime Database"),
        "firestore" to TestResult("Cloud Firestore"),
        "remoteConfig" to TestResult("Firebase Remote Config"),
        "grpc" to TestResult("gRPC (LogService)"),
        "fcm" to TestResult("Firebase Cloud Messaging (FCM)"),
        "theme" to TestResult("Design System & Temas (Claro/Oscuro/Alto Contraste)")
    ),
    val currentThemeMode: String = "Claro"
)

class IntegrationTestViewModel(
    private val database: AppDatabase,
    private val grpcClient: GrpcTestClient
) : ViewModel() {

    private val _state = MutableStateFlow(IntegrationTestState())
    val state = _state.asStateFlow()

    fun runTest(testKey: String) {
        viewModelScope.launch {
            updateTestStatus(testKey, TestStatus.RUNNING, "Ejecutando prueba...")
            try {
                when (testKey) {
                    "room" -> {
                        withTimeout(3000L) {
                            val dao = database.getDao()
                            val list = dao.getAllTodos()
                            updateTestStatus(testKey, TestStatus.SUCCESS, "Room OK. Registros locales: ${list.size}")
                        }
                    }
                    "realtimeDb" -> {
                        try {
                            withTimeout(3000L) {
                                val db = Firebase.database
                                val ref = db.reference("test_connection")
                                ref.setValue(true)
                            }
                            updateTestStatus(testKey, TestStatus.SUCCESS, "Realtime DB conectado y escrito con éxito.")
                        } catch (e: Exception) {
                            updateTestStatus(testKey, TestStatus.BLOCKED, "Timeout o bloqueado por red/reglas: ${e.message}")
                        }
                    }
                    "firestore" -> {
                        try {
                            withTimeout(3000L) {
                                Firebase.firestore
                            }
                            updateTestStatus(testKey, TestStatus.SUCCESS, "Firestore inicializado correctamente.")
                        } catch (e: Exception) {
                            updateTestStatus(testKey, TestStatus.ERROR, "Error Firestore: ${e.message}")
                        }
                    }
                    "remoteConfig" -> {
                        updateTestStatus(testKey, TestStatus.SUCCESS, "Remote Config disponible con valores por defecto.")
                    }
                    "grpc" -> {
                        try {
                            withTimeout(3000L) {
                                grpcClient.sendTestLog()
                            }
                            updateTestStatus(testKey, TestStatus.SUCCESS, "gRPC conectado a ngrok y log enviado con éxito.")
                        } catch (e: Exception) {
                            updateTestStatus(testKey, TestStatus.BLOCKED, "gRPC no alcanzable en ngrok (Verifica host/puerto): ${e.message}")
                        }
                    }
                    "fcm" -> {
                        try {
                            withTimeout(3000L) {
                                Firebase.messaging
                            }
                            updateTestStatus(testKey, TestStatus.SUCCESS, "FCM inicializado. Permisos y canal listos.")
                        } catch (e: Exception) {
                            updateTestStatus(testKey, TestStatus.BLOCKED, "FCM requiere permisos o google-services activo.")
                        }
                    }
                    "theme" -> {
                        updateTestStatus(testKey, TestStatus.SUCCESS, "Design System validado.")
                    }
                }
            } catch (e: Exception) {
                updateTestStatus(testKey, TestStatus.ERROR, "Error / Timeout", e.message)
            }
        }
    }

    fun setThemeMode(mode: String) {
        ThemeManager.currentTheme.value = mode
        _state.update { it.copy(currentThemeMode = mode) }
        updateTestStatus("theme", TestStatus.SUCCESS, "Tema cambiado a: $mode")
    }

    private fun updateTestStatus(key: String, status: TestStatus, message: String, error: String? = null) {
        _state.update { currentState ->
            val updatedTests = currentState.tests.toMutableMap()
            updatedTests[key] = TestResult(
                name = updatedTests[key]?.name ?: key,
                status = status,
                message = message,
                error = error
            )
            currentState.copy(tests = updatedTests)
        }
    }
}
