# Prism Motion

Aplicación interactiva para aprender y experimentar con las animaciones de Jetpack Compose y Compose Multiplatform.

![Portada de Prism Motion](docs/images/portada.png)

> *Nota: Reemplazar esta imagen por una captura real de la aplicación en la carpeta `docs/images/`.*

---

## 1. Portada y presentación del proyecto

- **Nombre:** Prism Motion
- **Descripción:** Una galería interactiva de laboratorios de animación desarrollada en Kotlin Multiplatform que permite explorar, tocar y comprender en tiempo real el comportamiento de las APIs oficiales de animación de Jetpack Compose.
- **Objetivo principal:** Facilitar el aprendizaje práctico de la animación en interfaces modernas mediante experimentación directa.
- **Tecnologías principales:** Kotlin Multiplatform, Compose Multiplatform, Jetpack Compose Animation, Material 3.
- **Plataforma principal:** Android (con arquitectura preparada para multiplataforma).

---

## 2. ¿Qué es Prism Motion?

Prism Motion es una herramienta educativa y de referencia visual diseñada para desarrolladores, diseñadores y entusiastas de Android. 

En lugar de explicar las animaciones mediante código estático o teoría abstracta, la aplicación presenta **14 laboratorios interactivos**. Cada laboratorio aísla una técnica específica (como resortes físicos, curvas de aceleración, transiciones de visibilidad o cambios de diseño) y ofrece controles deslizantes, selectores de chips y botones en vivo para modificar los parámetros y sentir instantáneamente el resultado.

También incluye una sección de **Ejemplo interactivo** que demuestra cómo aplicar estas animaciones en un caso de uso comercial real (una tienda virtual de E-Commerce), comparando una interfaz estática frente a una interfaz fluida.

---

## 3. Tecnologías utilizadas

| Tecnología | Función en Prism Motion |
|---|---|
| **Kotlin** | Lenguaje de programación principal, moderno y conciso. |
| **Kotlin Multiplatform (KMP)** | Permite compartir la lógica y la interfaz entre plataformas compatibles. |
| **Compose Multiplatform (CMP)** | Framework declarativo para construir interfaces visuales multiplataforma. |
| **Jetpack Compose Animation** | El motor nativo de animaciones (`animate*AsState`, `spring`, `tween`, etc.). |
| **Material 3** | Sistema de diseño estético y componentes adaptativos. |
| **Coroutines** | Gestión de operaciones asíncronas y retrasos escalonados (*stagger*). |
| **Canvas** | Renderizado de gráficos, ondas fluidas y trazos personalizados. |
| **Gradle** | Sistema de automatización de compilación y dependencias. |

---

## 4. Librerías y APIs de animación utilizadas

Prism Motion utiliza exclusivamente las **APIs nativas y oficiales de Jetpack Compose Animation**. No utiliza librerías de terceros como Lottie o Rive.

| Librería / API | ¿Para qué sirve? | ¿Dónde se utiliza en Prism Motion? |
|---|---|---|
| `animate*AsState` | Anima automáticamente un valor discreto (Dp, Color, Float, Size) al cambiar de estado. | Laboratorio Value Morph, Showcase E-Commerce. |
| `spring` | Simula física de resortes con amortiguación y rigidez configurables. | Laboratorio Spring Playground, Gesture Physics. |
| `CubicBezierEasing` | Define curvas de aceleración y desaceleración personalizadas. | Laboratorio Easing Lab. |
| `AnimatedVisibility` | Muestra u oculta elementos con transiciones de desvanecimiento, escala y deslizamiento. | Laboratorio Visibility Mixer, Notificaciones. |
| `AnimatedContent` / `Crossfade` | Transiciona fluidamente entre diferentes contenidos visuales o pestañas. | Laboratorio Content Swap, Pestañas de envío. |
| `updateTransition` | Coordina múltiples propiedades simultáneamente asociadas a un estado común. | Laboratorio Transition Studio. |
| `rememberInfiniteTransition` | Ejecuta animaciones continuas en bucle (pulsos, órbitas, shimmer). | Laboratorio Infinite Loops, Canvas Playground. |
| `Animatable` | Controla animaciones imperativas mediante corrutinas y gestos táctiles. | Laboratorio Gesture Physics, Sequencing & Stagger. |
| `animateContentSize` | Anima automáticamente cambios de tamaño en contenedores y tarjetas. | Laboratorio Layout Magic, Resumen del carrito. |
| `Canvas` | Dibuja gráficos, ondas y partículas fotograma a fotograma. | Laboratorio Canvas Playground. |

---

## 5. Instalación y ejecución del proyecto

Para ejecutar Prism Motion en tu equipo:

1. **Requisitos:**
   - Android Studio (versión Jellyfish o superior recomendada con soporte para Compose Multiplatform).
   - JDK 17 o superior.
2. **Abrir el proyecto:**
   - Clona o descarga el repositorio y ábrelo en Android Studio.
3. **Sincronizar Gradle:**
   - Deja que Android Studio descargue las dependencias y sincronice el proyecto automáticamente.
4. **Ejecutar:**
   - Conecta un dispositivo físico Android o inicia un emulador (API 24 o superior).
   - Selecciona la configuración de ejecución `:androidApp` y presiona el botón **Run ▶**.
   - También puedes compilar desde la terminal con `./gradlew :androidApp:assembleDebug`.

---

## 6. Guía de navegación y pantallas

### 6.1. Pantalla de Introducción (Welcome / Portada)
- **Descripción:** Es la pantalla de bienvenida o portada animada que recibe al usuario al abrir la aplicación.
- **Funcionalidades:** Presenta la identidad visual de Prism Motion con un logotipo animado con pulso y resplandor, tarjetas de características principales y el botón de acceso para comenzar la experiencia.
- **Captura de pantalla:**
  ![Portada](docs/images/portada.png)

### 6.2. Pantalla Principal (Home)
- **Descripción:** Es la pantalla desde la cual el usuario puede acceder a los diferentes laboratorios de animación.
- **Funcionalidades:** Explorar laboratorios, filtrar por categorías, acceder al detalle de cada animación y abrir los ajustes globales.
- **Captura de pantalla:**
  ![Pantalla principal](docs/images/pantalla-principal.png)

### 6.3. Panel de Ajustes (SettingsBottomSheet)
- **Descripción:** Hoja inferior flotante para configurar las preferencias globales de animación y apariencia.
- **Funcionalidades:** Cambiar entre los tres temas visuales, ajustar el multiplicador de velocidad y activar el modo Reducir Movimiento.
- **Captura de pantalla:**
  ![Configuración](docs/images/configuracion.png)

---

## 7. Documentación de los laboratorios de animación

Prism Motion incluye **14 laboratorios** interactivos:

### 7.1. Value Morph
- **Objetivo:** Aprender cómo animar automáticamente valores de la interfaz cuando cambian.
- **APIs utilizadas:** `animate*AsState`, `tween`, `spring`.
- **Captura de pantalla:** ![Value Morph](docs/images/value-morph.png)

### 7.2. Spring Playground
- **Objetivo:** Comprender las animaciones físicas basadas en resortes.
- **APIs utilizadas:** `spring`, `dampingRatio`, `stiffness`.
- **Captura de pantalla:** ![Spring Playground](docs/images/spring-playground.png)

### 7.3. Easing Lab
- **Objetivo:** Explorar curvas de aceleración y desaceleración.
- **APIs utilizadas:** `CubicBezierEasing`, `LinearEasing`, `FastOutSlowInEasing`.
- **Captura de pantalla:** ![Easing Lab](docs/images/easing-lab.png)

### 7.4. Visibility Mixer
- **Objetivo:** Controlar la visibilidad de componentes con transiciones.
- **APIs utilizadas:** `AnimatedVisibility`, `fadeIn`, `slideInVertically`, `scaleIn`.
- **Captura de pantalla:** ![Visibility Mixer](docs/images/visibility-mixer.png)

### 7.5. Content Swap
- **Objetivo:** Transicionar entre diferentes contenidos dinámicos.
- **APIs utilizadas:** `AnimatedContent`, `Crossfade`.
- **Captura de pantalla:** ![Content Swap](docs/images/content-swap.png)

### 7.6. Transition Studio
- **Objetivo:** Coordinar múltiples propiedades con un estado común.
- **APIs utilizadas:** `updateTransition`, `animateColor`, `animateDp`.
- **Captura de pantalla:** ![Transition Studio](docs/images/transition-studio.png)

### 7.7. Infinite Loops
- **Objetivo:** Crear animaciones continuas en bucle.
- **APIs utilizadas:** `rememberInfiniteTransition`, `infiniteRepeatable`.
- **Captura de pantalla:** ![Infinite Loops](docs/images/infinite-loops.png)

### 7.8. Gesture Physics
- **Objetivo:** Combinar gestos táctiles con físicas de resorte.
- **APIs utilizadas:** `Animatable`, `pointerInput`, `detectDragGestures`.
- **Captura de pantalla:** ![Gesture Physics](docs/images/gesture-physics.png)

### 7.9. Sequencing & Stagger
- **Objetivo:** Ejecutar animaciones de forma secuencial y escalonada (*stagger*).
- **APIs utilizadas:** `Animatable`, corrutinas y retrasos temporales.
- **Captura de pantalla:** ![Sequencing Stagger](docs/images/sequencing-stagger.png)

### 7.10. Layout Magic
- **Objetivo:** Animar cambios automáticos de tamaño en contenedores.
- **APIs utilizadas:** `animateContentSize`.
- **Captura de pantalla:** ![Layout Magic](docs/images/layout-magic.png)

### 7.11. Shared Elements
- **Objetivo:** Mantener continuidad visual entre vistas y tarjetas.
- **APIs utilizadas:** Transiciones de elementos compartidos.
- **Captura de pantalla:** ![Shared Elements](docs/images/shared-elements.png)

### 7.12. Canvas Playground
- **Objetivo:** Dibujar y animar gráficos personalizados.
- **APIs utilizadas:** `Canvas`, `Path`, `drawPath`.
- **Captura de pantalla:** ![Canvas Playground](docs/images/canvas-playground.png)

### 7.13. Text Motion
- **Objetivo:** Animar contadores numéricos y texto.
- **APIs utilizadas:** `animateIntAsState`.
- **Captura de pantalla:** ![Text Motion](docs/images/text-motion.png)

### 7.14. Ejemplo interactivo
- **Objetivo:** Demostrar 15 animaciones aplicadas a un caso de uso real de E-Commerce.
- **APIs utilizadas:** `AnimatedVisibility`, `animate*AsState`, `AnimatedContent`, `animateContentSize`, `InfiniteTransition`, etc.
- **Captura de pantalla:** ![Ejemplo interactivo](docs/images/ejemplo-con-animaciones.png)

---

## 8. Ejemplo interactivo de aplicación real

El laboratorio **Ejemplo interactivo** simula una tienda virtual de tecnología (*TechStore*) para demostrar el valor real de las animaciones en producción.

- **Modo "Sin animaciones":** Las acciones ocurren de forma instantánea y estática (cambios bruscos).
- **Modo "Con animaciones":** Las mismas acciones incorporan transiciones fluidas, resortes físicos, contadores numéricos animados y notificaciones deslizantes.
- **Funcionalidades:** Catálogo de productos con estados independientes, favoritos con corazones animados, gestión de carrito, cupones de descuento y diálogo de éxito de compra.

![Ejemplo sin animaciones](docs/images/ejemplo-sin-animaciones.png)

![Ejemplo con animaciones](docs/images/ejemplo-con-animaciones.png)

---

## 9. Temas visuales

Prism Motion es compatible con tres modos de apariencia:

- **Dark Cosmic:** Tonos morados profundos (`#120B24`), superficies de vidrio translúcido y acentos luminosos.
- **Light:** Superficies blancas y limpias con excelente contraste y legibilidad.
- **High Contrast:** Fondos negros absolutos y colores primarios muy llamativos para accesibilidad visual avanzada.

![Tema oscuro](docs/images/tema-oscuro.png)

![Tema claro](docs/images/tema-claro.png)

![Tema de alto contraste](docs/images/tema-alto-contraste.png)

---

## 10. Configuración y accesibilidad

- **Velocidad global:** Acelera o ralentiza la reproducción de todas las animaciones del sistema.
- **Reducir movimiento (*Reduce Motion*):** Desactiva animaciones decorativas para cumplir con pautas de accesibilidad y confort visual.
- **Áreas táctiles:** Todos los botones y controles cumplen con el estándar mínimo de 48dp.

---

## 11. Estructura del proyecto para desarrolladores

```text
commonMain/kotlin/ucb/vargasn/project/
   │
   ├── App.kt                      // Composable raíz y ThemeManager
   ├── theme/                      // Color.kt, Theme.kt, Type.kt, Brush.kt
   ├── core/
   │     ├── components/           // LabScaffold, GlassCard, GlowButton, ChipSelector, etc.
   │     └── model/                // Lab, LabCategory, MotionSettings
   ├── presentation/
   │     ├── navigation/           // AppNavHost, NavRoute
   │     └── screen/               // HomeScreen, SettingsBottomSheet
   └── labs/                       // Los 14 laboratorios interactivos (XxxLab.kt)
```

---

## 12. Cómo agregar un laboratorio nuevo

1. Crea un archivo nuevo en `labs/` (ej. `MiNuevoLab.kt`) exponiendo un composable `fun MiNuevoLabContent(modifier: Modifier = Modifier, resetToken: Int = 0)`.
2. Añade una entrada en `LabRegistry.kt` asignándole un identificador único, título, categoría y color de acento.
3. El laboratorio aparecerá automáticamente en la pantalla principal y en la navegación.

---

## 13. Glosario sencillo

- **Animación:** Cambio gradual y suave en las propiedades visuales de un componente a lo largo del tiempo.
- **Estado (*State*):** Valor mutable que define qué debe mostrar la interfaz en un momento dado.
- **Recomposición:** Proceso mediante el cual Compose vuelve a ejecutar los composables cuando su estado cambia.
- **Spring (Resorte):** Animación basada en física que calcula el movimiento en función de la rigidez y el amortiguamiento.
- **Tween:** Animación basada en interpolación lineal o curva durante una duración fija en milisegundos.
- **Stagger (Escalonado):** Retraso calculado entre la ejecución de animaciones de varios elementos en secuencia.

---

## 14. Conclusión

Prism Motion ofrece un entorno completo y accesible para dominar las animaciones en Compose Multiplatform. Combinando teoría interactiva y un caso de uso real de E-Commerce, demuestra que el movimiento bien implementado transforma una interfaz ordinaria en una experiencia memorable y profesional.

---

## 15. Organización de las capturas de pantalla

Las capturas de pantalla de la aplicación deben ubicarse en la carpeta:

`docs/images/`

Con nombres descriptivos como `portada.png`, `pantalla-principal.png`, `configuracion.png`, `value-morph.png`, `spring-playground.png`, `easing-lab.png`, `visibility-mixer.png`, `content-swap.png`, `transition-studio.png`, `infinite-loops.png`, `gesture-physics.png`, `sequencing-stagger.png`, `layout-magic.png`, `shared-elements.png`, `canvas-playground.png`, `text-motion.png`, `ejemplo-sin-animaciones.png`, `ejemplo-con-animaciones.png`, `tema-oscuro.png`, `tema-claro.png`, `tema-alto-contraste.png`.
