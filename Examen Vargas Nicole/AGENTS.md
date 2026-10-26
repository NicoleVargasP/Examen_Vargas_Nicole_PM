# Tarea: Integrar cliente gRPC en el proyecto Kotlin Multiplatform

## 1. Objetivo principal

Necesito integrar correctamente un cliente gRPC en mi proyecto Kotlin Multiplatform (KMP), utilizando el servidor Spring Boot que ya está implementado y funcionando.

El objetivo inicial es que la aplicación Android pueda enviar logs al servidor mediante gRPC, recibir la respuesta y comprobar que la comunicación funciona correctamente. Si la estructura actual del proyecto lo permite, deja preparada la implementación para enviar logs desde la aplicación de manera organizada y reutilizable.

**No empieces modificando archivos inmediatamente. Primero analiza el proyecto completo, identifica la implementación existente y determina cuál es la forma correcta de integrar gRPC sin romper el funcionamiento actual.**

## 2. Contexto del proyecto

Mi proyecto utiliza Kotlin Multiplatform con esta configuración general:

- Kotlin Multiplatform.
- Compose Multiplatform.
- Android e iOS (`iosArm64` e `iosSimulatorArm64`).
- Arquitectura organizada en módulos y capas.
- Gradle Kotlin DSL (`build.gradle.kts`).
- Version Catalog en `gradle/libs.versions.toml`.
- Código compartido en `shared/src/commonMain`.
- Código específico de Android en `shared/src/androidMain`.

El módulo `shared` ya contiene dependencias de Compose, Koin, Ktor, Kotlin Serialization, Firebase y otras tecnologías. No elimines ni reemplaces estas dependencias por conveniencia.

En el proyecto ya existen archivos relacionados con el envío de logs, por lo que debes inspeccionarlos antes de crear otros nuevos.

### Archivos conocidos que debes revisar

- `shared/build.gradle.kts`
- `gradle/libs.versions.toml`
- `shared/src/commonMain/proto/LogApi.proto`
- `shared/src/commonMain/kotlin/org/ucb/appp1/log/LogService.kt`
- `shared/src/androidMain/kotlin/org/ucb/appp1/log/LogService.android.kt`
- `shared/src/commonMain/kotlin/org/ucb/appp1/log/data/repository/LogRepository.kt`

También debes localizar e inspeccionar los archivos Gradle de configuración del proyecto, incluyendo `settings.gradle.kts` y el módulo de aplicación Android.

Si los archivos tienen nombres o rutas diferentes, utiliza las rutas reales del proyecto y no crees duplicados innecesarios.

## 3. Servidor gRPC existente

El servidor ya está implementado con Spring Boot y se ejecuta correctamente.

La información confirmada de ejecución es:

- Tecnología: Spring Boot.
- Puerto gRPC local: `9090`.
- Servicio registrado: `LogService`.
- El servidor registra también los servicios de reflexión y health.
- El servidor se inicia mediante el archivo `logs-0.0.1-SNAPSHOT.jar`.

Los logs de arranque confirman que el servidor está escuchando en el puerto `9090`.

También configuré un túnel TCP con ngrok. La dirección pública obtenida durante la prueba fue:

- Host: `0.tcp.sa.ngrok.io`
- Puerto público: `28082`

Esta dirección es temporal y puede cambiar al reiniciar el túnel. No la dejes codificada permanentemente en el código de producción.

El servidor y el túnel deben permanecer ejecutándose durante las pruebas que dependan de ellos.

## 4. Contrato del servicio

El archivo `.proto` compartido contiene el siguiente contrato. Respeta los nombres de los mensajes, campos, enumeraciones y operaciones:

```proto
syntax = "proto3";

option java_package = "com.develoop.logs";

service LogService {
  rpc send(LogRequest) returns (LogResponse);
}

message LogRequest {
  repeated LogData logs = 1;
}

message LogData {
  bytes androidId = 1;
  bytes appInstanceId = 2;
  ELogLevel logLevel = 3;
  string message = 4;
  string stackTrace = 5;
  int64 serverTimeStamp = 6;
  int64 mobileTimeStamp = 7;
  int32 versionCode = 8;
  string userId = 9;
}

message LogResponse {
  ELogResultCode resultCode = 1;
}

enum ELogResultCode {
  LOG_RESULT_UNDEFINED = 0;
  LOG_RESULT_ERROR = 1;
  LOG_RESULT_OK = 2;
}

enum ELogLevel {
  LEVEL_UNKNOWN = 0;
  LEVEL_INFO = 1;
  LEVEL_DEBUG = 2;
  LEVEL_TRACE = 3;
  LEVEL_WARN = 4;
  LEVEL_ERROR = 5;
  LEVEL_FATAL = 6;
}
```

Antes de modificar el `.proto`, comprueba si existe alguna diferencia entre este contrato, el archivo real del proyecto y el servicio generado o implementado en el backend.

No cambies el contrato para hacer que compile sin verificar antes la implementación del servidor.

Presta especial atención a los campos `bytes`: comprueba qué tipo genera la herramienta utilizada y cómo deben construirse correctamente en Kotlin. No conviertas identificadores de bytes a texto arbitrariamente ni inventes datos.

## 5. Revisar y decidir la tecnología gRPC

En el proyecto hay indicios de que se utiliza Square Wire, incluyendo referencias a:

- `com.squareup.wire`
- `wire-runtime`
- `wire-grpc-client`
- `GrpcClient.Builder`
- `LogServiceClient`

También aparecen dependencias de gRPC Java/Kotlin y Protobuf en el Version Catalog.

**Debes determinar cuál de estas tecnologías utiliza realmente la implementación actual y cuál corresponde a los requisitos del ejercicio.**

Procedimiento obligatorio:

1. Inspecciona el plugin de generación de código configurado en Gradle.
2. Comprueba cómo se genera actualmente el código a partir de los archivos `.proto`.
3. Revisa los imports y las clases utilizadas en `LogService.android.kt`.
4. Comprueba si existen clases generadas para `LogService`, `LogRequest`, `LogResponse` y `LogData`.
5. Revisa el PDF o las instrucciones del ejercicio si están disponibles en el proyecto.
6. Decide una estrategia compatible con la implementación existente.

No mezcles Square Wire con gRPC Java/Kotlin dentro de una misma implementación sin una razón técnica justificada.

No añadas varios plugins de generación para el mismo `.proto` por si acaso. No generes dos implementaciones diferentes del mismo servicio. Si fuera necesario cambiar de tecnología, explica primero por qué es necesario y limita los cambios a los archivos afectados.

Considera que el proyecto es KMP: una solución que funciona en Android no necesariamente funciona en `commonMain` o iOS. No coloques dependencias exclusivas de Android en código común.

## 6. Arquitectura esperada

Respeta la arquitectura actual del proyecto.

Antes de crear clases nuevas, determina si las existentes cumplen estas responsabilidades o si necesitan ajustes:

- **Servicio gRPC:** establecer la conexión con el servidor y ejecutar la operación remota.
- **Repositorio:** ofrecer una interfaz sencilla para que el resto de la aplicación pueda enviar logs, sin depender directamente de los detalles del transporte.
- **Modelos y mapeadores:** convertir los datos de la aplicación a los mensajes generados por el `.proto`, cuando sea necesario.
- **Inyección de dependencias:** registrar las implementaciones utilizando el mecanismo que ya usa el proyecto, como Koin, si corresponde.
- **Presentación o prueba:** permitir verificar el envío y mostrar el resultado sin introducir lógica de transporte en la interfaz.

No crees todas estas capas de nuevo si ya existen. Reutiliza las actuales y modifica únicamente lo necesario.

Si el código utiliza `expect`/`actual`, respeta esa estrategia y verifica que las implementaciones tengan firmas compatibles.

No implementes toda la lógica dentro de una pantalla ni conviertas el repositorio en una clase que haga simultáneamente conexión, mapeo, presentación y gestión de errores.

## 7. Configuración de conexión

Implementa la conexión de forma compatible con el cliente elegido y con el servidor real.

Distingue estos escenarios:

### Escenario A: emulador Android y servidor ejecutado en la misma computadora

Si el servidor se ejecuta en el equipo anfitrión, el emulador Android normalmente puede acceder a él mediante:

`10.0.2.2:9090`

Comprueba si este escenario es apropiado para las pruebas locales.

### Escenario B: túnel ngrok

Si el ejercicio exige utilizar el túnel TCP, configura el cliente con el host y el puerto públicos vigentes.

No supongas que un túnel TCP y una conexión gRPC funcionan automáticamente por el mero hecho de que el puerto esté abierto. Verifica la compatibilidad del transporte, HTTP/2 y la configuración de TLS o conexión sin TLS que corresponda al servidor y al cliente seleccionados.

No desactives la seguridad de manera global ni introduzcas excepciones inseguras para ocultar errores de conexión.

### Requisitos de configuración

- No dupliques las direcciones en varias clases.
- Utiliza una configuración centralizada y apropiada para el proyecto.
- Evita guardar credenciales o tokens en el código fuente.
- No dejes el host temporal de ngrok como dirección definitiva de producción.
- Explica cómo cambiar entre el servidor local y el túnel.
- Comprueba si Android necesita permisos de Internet.
- Revisa las restricciones de tráfico HTTP sin TLS que puedan afectar a la implementación.
- No afirmes que una conexión funciona hasta haber realizado una prueba real.

## 8. Implementación funcional

Implementa el flujo mínimo de envío de logs utilizando el método `send` del servicio.

El flujo debe:

1. Construir un `LogRequest` válido.
2. Incluir al menos un elemento `LogData` de prueba.
3. Asignar correctamente los tipos de los campos del `.proto`.
4. Enviar la petición mediante el cliente gRPC elegido.
5. Esperar la respuesta según el modelo asíncrono de la librería.
6. Interpretar `LogResponse.resultCode`.
7. Diferenciar una respuesta correcta de una respuesta de error.
8. Gestionar errores de conexión, timeout y fallos del servidor.
9. Evitar bloquear el hilo principal de Android.
10. Exponer un resultado que pueda utilizar la interfaz o el código de prueba.

Utiliza coroutines si son compatibles con la librería elegida y con la arquitectura existente. No añadas APIs de suspensión ficticias ni asumas que todas las librerías gRPC ofrecen la misma interfaz.

El nivel de log de prueba debe ser válido según `ELogLevel`. Los identificadores, timestamps, versión y `userId` deben construirse de forma coherente con el contrato. Si no existe información real para algún campo, utiliza un valor de prueba explícito solo en la prueba y documenta qué representa.

No inventes una respuesta del servidor ni simules el éxito del envío.

## 9. Gestión de errores y diagnóstico

Diferencia claramente los siguientes casos:

- No se puede resolver el host.
- No se puede establecer la conexión TCP.
- El servidor no está ejecutándose.
- El túnel ngrok está cerrado o utiliza un endpoint antiguo.
- Existe una incompatibilidad de TLS o HTTP/2.
- El servicio o método RPC no coincide con el contrato.
- La petición no se puede serializar.
- El servidor responde con un código de resultado distinto de `LOG_RESULT_OK`.
- El servidor devuelve un error gRPC.
- La operación supera el timeout.

Los mensajes de error deben ser útiles para diagnosticar el problema. No captures todas las excepciones para devolver simplemente `false` ni ocultes la causa original.

No registres tokens, contraseñas, datos personales ni identificadores sensibles completos en los logs.

## 10. Interfaz de prueba

Primero inspecciona la navegación y las pantallas existentes.

Si ya existe una pantalla adecuada, reutilízala. Si no existe, crea una pantalla de prueba sencilla y coherente con el diseño actual para comprobar el envío.

La prueba debería permitir:

- Iniciar manualmente el envío de un log de prueba.
- Mostrar un estado de carga mientras se realiza la petición.
- Indicar si el servidor respondió correctamente.
- Mostrar el `resultCode` recibido.
- Presentar un mensaje comprensible si falla la comunicación.

No rediseñes toda la aplicación ni modifiques pantallas que no estén relacionadas con esta tarea.

No agregues credenciales, datos ficticios de usuarios reales ni información sensible.

## 11. Restricciones importantes

- No elimines código existente sin justificarlo.
- No cambies la arquitectura general del proyecto.
- No cambies versiones de Kotlin, AGP, Compose, Koin o Gradle por conveniencia.
- No reemplaces Ktor, Firebase u otras tecnologías no relacionadas.
- No dupliques los archivos `.proto`.
- No crees clases con nombres que ya existen.
- No cambies paquetes o namespaces sin necesidad.
- No añadas dependencias que ya estén declaradas y sean compatibles.
- No actualices indiscriminadamente el Version Catalog.
- No modifiques iOS si la implementación solicitada es exclusivamente Android, salvo que sea necesario para mantener la compilación KMP.
- No elimines pruebas existentes.
- No des por válida una implementación solo porque compila.
- No marques la tarea como terminada si no has comprobado los resultados de las pruebas.

Si detectas un problema que depende del backend o de la base de datos, identifica exactamente qué evidencia lo demuestra. No atribuyas un error de conexión a la base de datos sin evidencia.

## 12. Plan de trabajo obligatorio

### Fase 1: diagnóstico

Antes de modificar código, entrega un resumen breve con:

- Archivos relevantes encontrados.
- Tecnología gRPC que utiliza actualmente el proyecto.
- Herramienta de generación de código y ubicación de los archivos `.proto`.
- Problemas detectados en Gradle o en las clases existentes.
- Estrategia de implementación recomendada.
- Lista exacta de archivos que propones modificar o crear.

Si falta información del backend o del ejercicio, indícalo claramente. No inventes detalles.

### Fase 2: implementación

Una vez realizado el diagnóstico, implementa la solución en los archivos necesarios.

Para cada cambio, explica brevemente qué se modifica y por qué. Respeta los paquetes, convenciones y nombres existentes.

### Fase 3: validación

Ejecuta las comprobaciones que permita el entorno:

1. Sincronización de Gradle.
2. Generación de código `.proto`, si corresponde.
3. Compilación del módulo `shared`.
4. Compilación del módulo Android.
5. Pruebas unitarias existentes y nuevas, si procede.
6. Verificación de que la petición gRPC se construye correctamente.
7. Prueba de conexión real, únicamente si el servidor y el endpoint están disponibles.

Si una prueba no puede ejecutarse, indica cuál, por qué y cómo puedo ejecutarla manualmente. No inventes resultados ni afirmes que se ejecutó una prueba que no realizaste.

### Fase 4: informe final

Al terminar, entrégame:

1. Diagnóstico inicial.
2. Lista de archivos modificados y creados.
3. Explicación de los cambios realizados.
4. Dependencias añadidas, eliminadas o conservadas.
5. Configuración del host y puerto.
6. Pasos exactos para iniciar el servidor, iniciar ngrok cuando corresponda y ejecutar la aplicación.
7. Resultado real de la compilación y las pruebas.
8. Errores pendientes y sus causas probables.
9. Instrucciones para verificar manualmente que el servidor recibe los logs.

## 13. Criterio de aceptación

La tarea se considera completada cuando:

- El proyecto compila en los módulos afectados.
- La implementación utiliza una sola estrategia de cliente y generación gRPC, coherente con el proyecto.
- El cliente construye una petición compatible con el `.proto`.
- La aplicación puede intentar enviar el log al servidor configurado.
- La respuesta se procesa correctamente.
- Los errores de conexión se pueden diagnosticar.
- No se han roto otras partes de la aplicación.
- Se informa claramente qué se comprobó y qué no se pudo comprobar.

**Prioridad:** preservar el código y la arquitectura existentes, seguir el contrato real del servidor y obtener una prueba funcional de comunicación antes de ampliar la implementación.