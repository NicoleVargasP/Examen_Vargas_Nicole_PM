# TASK — Crear documentación y manual de usuario de Prism Motion

Quiero que realices una documentación completa pero breve, clara, visual y fácil de entender de mi proyecto **Prism Motion**.

La documentación debe funcionar como una combinación de:

1. Manual de usuario: explicar cómo utilizar la aplicación y qué funcionalidades ofrece.
2. Documentación general del proyecto: explicar qué es la aplicación, cómo está organizada y qué tecnologías y librerías utiliza.
3. Guía de referencia para futuros desarrolladores: proporcionar la información necesaria para comprender el proyecto y poder continuarlo o modificarlo.

No quiero una documentación excesivamente técnica, extensa ni complicada. Debe poder entenderla una persona que no tenga conocimientos avanzados de programación, pero también debe incluir los detalles técnicos más importantes para alguien que quiera trabajar en el código.

---

## 1. Revisión previa del proyecto

Antes de redactar la documentación, inspecciona el proyecto completo y verifica cómo está implementado actualmente.

Revisa especialmente:

- La estructura de carpetas.
- Las pantallas existentes.
- La navegación.
- Los laboratorios de animación implementados.
- Los componentes reutilizables.
- El sistema de temas.
- Las configuraciones de accesibilidad.
- Las opciones y ajustes de la aplicación.
- Las animaciones utilizadas.
- Las librerías y dependencias del proyecto.
- Los archivos Gradle.
- Las funcionalidades realmente implementadas.

**La documentación debe reflejar el estado REAL del proyecto.**

No documentes funcionalidades que solamente estén planeadas, que estén incompletas o que no existan todavía.

Si encuentras diferencias entre la documentación previa, los archivos `TASKS.md` y la implementación real, prioriza el código actual y señala las funcionalidades que todavía no estén terminadas.

---

## 2. Formato y ubicación

Crea un archivo de documentación principal llamado:

`DOCUMENTACION.md`

Ubícalo en la raíz del proyecto.

Debe estar organizado con títulos, subtítulos, listas, tablas cuando sean útiles y explicaciones breves.

Quiero que sea visualmente agradable y fácil de leer en GitHub, GitLab, Android Studio o cualquier editor Markdown.

No conviertas la documentación en un archivo excesivamente extenso. Explica lo importante de forma clara y resumida.

---

## 3. Portada y presentación del proyecto

La documentación debe comenzar con una portada sencilla que incluya:

- Nombre: Prism Motion.
- Una breve descripción de la aplicación.
- Objetivo principal.
- Tecnologías principales.
- Plataforma principal.
- Un espacio para colocar una imagen representativa de la aplicación.

Utiliza un formato similar a:

```markdown
# Prism Motion

Aplicación interactiva para aprender y experimentar con las animaciones de Jetpack Compose.

![Portada de Prism Motion](docs/images/portada.png)
```

No inventes imágenes ni agregues imágenes inexistentes.

Si no existen imágenes disponibles, deja los espacios correspondientes preparados para que posteriormente pueda agregarlas.

---

## 4. ¿Qué es Prism Motion?

Explica de forma sencilla:

- Qué es la aplicación.
- Para qué sirve.
- Qué problema o necesidad busca resolver.
- A quién está dirigida.
- Qué se puede aprender utilizándola.
- Por qué se creó como una aplicación interactiva y no solamente como una presentación teórica.

La explicación debe ser comprensible para alguien que nunca ha utilizado Compose Animation.

---

## 5. Tecnologías utilizadas

Crea una sección que explique las tecnologías principales utilizadas en el proyecto.

Incluye las que realmente estén implementadas, por ejemplo:

- Kotlin.
- Kotlin Multiplatform.
- Compose Multiplatform.
- Jetpack Compose.
- Material 3.
- Coroutines.
- Canvas.
- Gradle.

Explica brevemente qué función cumple cada tecnología dentro de la aplicación.

Evita definiciones demasiado técnicas. Por ejemplo, no basta con colocar el nombre de una tecnología: explica para qué se utiliza dentro de Prism Motion.

---

## 6. Librerías y APIs de animación utilizadas

Esta es una de las secciones MÁS IMPORTANTES de la documentación.

Quiero que investigues exactamente qué APIs, librerías y herramientas de animación utiliza realmente el proyecto.

No quiero que simplemente escribas "Jetpack Compose Animation".

Identifica las dependencias relacionadas con animaciones y explica también las APIs de animación que se utilizan en el código.

Por ejemplo, verifica si se utilizan:

- `androidx.compose.animation`
- `androidx.compose.animation.core`
- `animate*AsState`
- `Animatable`
- `AnimationSpec`
- `spring`
- `tween`
- `keyframes`
- `snap`
- `Easing`
- `AnimatedVisibility`
- `AnimatedContent`
- `Crossfade`
- `updateTransition`
- `rememberInfiniteTransition`
- `animateContentSize`
- `animateItem`
- `SharedTransitionLayout`
- `sharedElement`
- `sharedBounds`
- Animaciones personalizadas mediante `Canvas`.
- `Path`.
- Animaciones relacionadas con gestos.
- `animateDecay`.
- `pointerInput`.
- `detectDragGestures`.

**No incluyas automáticamente todas estas APIs. Verifica cuáles se encuentran realmente implementadas en el proyecto.**

Organiza esta sección en una tabla con columnas similares a:

| Librería/API | ¿Para qué sirve? | ¿Dónde se utiliza? |
|---|---|---|
| animate*AsState | Permite animar automáticamente un valor cuando cambia. | Laboratorio Value Morph. |
| AnimatedVisibility | Permite mostrar u ocultar elementos con animaciones. | Laboratorio Visibility Mixer. |
| Animatable | Permite controlar una animación mediante código. | Laboratorio Gesture Physics. |

Utiliza ejemplos relacionados con los laboratorios reales de Prism Motion.

Después de la tabla, agrega una explicación sencilla de por qué se utilizan las APIs nativas de Compose Animation y qué ventajas ofrecen para el proyecto.

También identifica las dependencias Gradle exactas relacionadas con animaciones, mostrando sus nombres reales y versiones cuando estén disponibles.

---

## 7. Instalación y ejecución del proyecto

Incluye una guía breve para alguien que quiera ejecutar la aplicación desde Android Studio.

Explica:

- Requisitos básicos.
- Software necesario.
- Cómo abrir el proyecto.
- Cómo sincronizar Gradle.
- Cómo ejecutar la aplicación.
- Cómo utilizar un emulador o dispositivo Android físico.
- Comando de compilación si corresponde.

Utiliza los requisitos reales del proyecto.

No inventes configuraciones que no estén presentes.

Esta sección debe ser sencilla, con instrucciones paso a paso.

---

## 8. Guía de navegación y pantallas

Documenta todas las pantallas que existen actualmente en la aplicación.

Para cada pantalla incluye:

### Nombre de la pantalla

**¿Qué es?**  
Una breve explicación de su propósito.

**¿Qué permite hacer?**  
Describe sus funcionalidades principales.

**¿Cómo se utiliza?**  
Explica de forma sencilla cómo interactuar con ella.

**Elementos principales:**  
Enumera sus botones, controles, tarjetas, opciones y elementos importantes.

**Animaciones utilizadas:**  
Explica cuáles son las animaciones que se pueden experimentar en esa pantalla y qué ocurre cuando el usuario interactúa.

**Captura de pantalla:**  
Deja un espacio para colocar una imagen de la pantalla.

Utiliza un formato similar:

```markdown
### 3.1. Pantalla principal

**Descripción:**  
Es la pantalla desde la cual el usuario puede acceder a los diferentes laboratorios de animación.

**Funcionalidades:**
- Explorar los laboratorios disponibles.
- Filtrar por categorías.
- Acceder a cada laboratorio.
- Modificar las opciones generales de la aplicación.

**Captura de pantalla:**

![Pantalla principal](docs/images/pantalla-principal.png)

> Reemplazar esta imagen por una captura real de la aplicación.
```

Documenta todas las pantallas relevantes, sin omitir ninguna.

---

## 9. Documentación de los laboratorios de animación

Esta sección debe explicar los laboratorios que existen realmente en Prism Motion.

Cada laboratorio debe tener una descripción breve, sencilla y orientada al usuario.

Para cada uno incluye:

- Nombre.
- Objetivo.
- Qué animación enseña.
- Qué elementos puede modificar el usuario.
- Cómo interactuar con el laboratorio.
- Un ejemplo práctico de lo que ocurre.
- APIs de animación utilizadas.
- Un espacio para colocar una captura de pantalla.

No es necesario explicar todo el código de cada animación. La idea es que el lector comprenda qué está aprendiendo y cómo puede experimentar.

Utiliza un formato uniforme para todos los laboratorios.

Si los 14 laboratorios descritos en la documentación anterior siguen existiendo, verifica y documenta cada uno:

1. Value Morph.
2. Spring Playground.
3. Easing Lab.
4. Visibility Mixer.
5. Content Swap.
6. Transition Studio.
7. Infinite Loops.
8. Gesture Physics.
9. Sequencing & Stagger.
10. Layout Magic.
11. Shared Elements.
12. Canvas Playground.
13. Text Motion.
14. Ejemplo interactivo.

Si algún laboratorio ha cambiado de nombre, utiliza su nombre actual.

Si alguno no está implementado, no lo describas como funcional.

### Ejemplo de documentación de un laboratorio

```markdown
### Value Morph

**Objetivo:**  
Aprender cómo animar automáticamente valores de la interfaz cuando cambian.

**¿Qué permite experimentar?**
- Modificar el tamaño de un elemento.
- Cambiar su color.
- Ajustar su posición.
- Modificar su rotación.
- Experimentar con diferentes formas.

**¿Cómo funciona?**  
Cuando el usuario cambia uno de los controles, el elemento pasa gradualmente de su estado actual al nuevo estado mediante una animación.

**APIs utilizadas:**
- animate*AsState
- AnimationSpec
- tween
- spring

**Captura de pantalla:**

![Value Morph](docs/images/value-morph.png)
```

Adapta este ejemplo a cada laboratorio real.

---

## 10. Ejemplo interactivo de aplicación real

Documenta la pantalla que anteriormente se llamaba Real-World Showcase, utilizando su nombre actual, por ejemplo **Ejemplo interactivo**.

Explica:

- Qué aplicación de ejemplo representa.
- Qué elementos tiene.
- Cómo interactuar con los productos.
- Cómo funcionan los favoritos.
- Cómo funciona el carrito.
- Qué ocurre al cambiar cantidades.
- Cómo se actualizan los precios.
- Cómo se compara el modo sin animaciones con el modo animado.
- Qué animaciones se utilizan para mejorar la experiencia.
- Qué se puede aprender observando la comparación.

Incluye un espacio para capturas del modo sin animaciones y del modo con animaciones.

```markdown
![Ejemplo interactivo sin animaciones](docs/images/ejemplo-sin-animaciones.png)

![Ejemplo interactivo con animaciones](docs/images/ejemplo-con-animaciones.png)
```

Verifica que las funcionalidades descritas realmente estén implementadas.

---

## 11. Temas visuales

Documenta los temas de la aplicación.

Si actualmente existen los tres temas, explica:

### Dark Cosmic
Describe el estilo oscuro, los colores y la apariencia general.

### Light
Describe el estilo claro y sus características visuales.

### High Contrast
Explica que este tema prioriza la legibilidad, el contraste y la identificación clara de los elementos.

Incluye una explicación de cómo cambiar de tema desde la aplicación.

Deja espacios para capturas de cada tema:

```markdown
![Tema oscuro](docs/images/tema-oscuro.png)

![Tema claro](docs/images/tema-claro.png)

![Tema de alto contraste](docs/images/tema-alto-contraste.png)
```

---

## 12. Configuración y accesibilidad

Explica de forma sencilla las opciones de configuración que existen actualmente.

Documenta, si están implementadas:

- Selección de tema.
- Velocidad global de las animaciones.
- Reduce Motion.
- Otras configuraciones disponibles.

Explica cómo estas opciones afectan la experiencia de usuario.

Incluye también las características de accesibilidad que realmente estén implementadas, como:

- Tamaño de fuente adaptable.
- Contraste.
- Descripciones de elementos.
- Targets de interacción.
- Reducción de movimiento.

No afirmes que una característica está implementada si no la verificaste.

---

## 13. Estructura del proyecto para desarrolladores

Incluye una sección breve que explique cómo está organizado el código.

Muestra la estructura real y explica el propósito de las carpetas principales.

Por ejemplo, si existen:

- `theme`
- `core`
- `components`
- `model`
- `presentation`
- `navigation`
- `screen`
- `labs`

Explica de forma sencilla qué responsabilidad tiene cada una.

Incluye un pequeño esquema del flujo general:

```text
Aplicación
   │
   ├── Navegación
   │
   ├── Pantalla principal
   │
   ├── Laboratorios
   │      ├── Animaciones
   │      ├── Controles
   │      └── Estados
   │
   ├── Componentes reutilizables
   │
   └── Temas y configuración
```

Adapta el esquema a la arquitectura real del proyecto.

No documentes todas las clases, funciones o archivos individualmente. Solo los elementos que ayudan a comprender y mantener la aplicación.

---

## 14. Cómo agregar o modificar un laboratorio

Incluye una guía básica para un futuro desarrollador que quiera agregar un laboratorio nuevo.

Explica brevemente:

1. Dónde se implementan los laboratorios.
2. Cómo se registran para aparecer en la aplicación.
3. Cómo se utilizan los componentes compartidos.
4. Cómo se manejan los estados.
5. Cómo se integran las animaciones.
6. Cómo respetar los temas.
7. Cómo respetar Reduce Motion y la velocidad global, si corresponde.
8. Qué aspectos debe revisar antes de considerar terminado un laboratorio.

Esta guía debe ser corta y práctica.

---

## 15. Glosario sencillo

Incluye un pequeño glosario de conceptos importantes utilizados en Prism Motion.

Por ejemplo:

- Animación.
- Estado.
- Recomposición.
- Transición.
- Easing.
- Spring.
- Tween.
- Animación infinita.
- Stagger.
- Canvas.
- Shared Element.

Explica cada concepto con palabras sencillas y, cuando sea posible, utiliza ejemplos de la aplicación.

No debe ser un glosario excesivamente extenso.

---

## 16. Conclusión

Termina la documentación con un breve resumen sobre:

- Qué ofrece Prism Motion.
- Qué conocimientos permite practicar.
- Qué importancia tiene el uso de animaciones en las interfaces modernas.
- Qué ventajas tiene experimentar con animaciones utilizando ejemplos interactivos.

---

## 17. Organización de las capturas de pantalla

Quiero que la documentación deje espacios claros para agregar capturas reales de las pantallas.

Organiza las imágenes en una carpeta:

`docs/images/`

Utiliza nombres descriptivos, por ejemplo:

- `portada.png`
- `pantalla-principal.png`
- `configuracion.png`
- `value-morph.png`
- `spring-playground.png`
- `easing-lab.png`
- `visibility-mixer.png`
- `content-swap.png`
- `transition-studio.png`
- `infinite-loops.png`
- `gesture-physics.png`
- `sequencing-stagger.png`
- `layout-magic.png`
- `shared-elements.png`
- `canvas-playground.png`
- `text-motion.png`
- `ejemplo-sin-animaciones.png`
- `ejemplo-con-animaciones.png`
- `tema-oscuro.png`
- `tema-claro.png`
- `tema-alto-contraste.png`

No es obligatorio crear archivos de imágenes vacíos.

Si las capturas no existen, utiliza referencias Markdown preparadas para que posteriormente pueda colocar las imágenes en esa carpeta.

Procura que las capturas estén ubicadas junto a la explicación de la pantalla correspondiente, no todas al final del documento.

---

## 18. Estilo de redacción

La documentación debe:

- Estar completamente en español.
- Utilizar un lenguaje natural, claro y sencillo.
- Evitar párrafos excesivamente largos.
- Evitar explicaciones técnicas innecesarias.
- Utilizar ejemplos prácticos.
- Tener una estructura ordenada.
- Ser fácil de consultar rápidamente.
- No repetir la misma información en varias secciones.
- No ser demasiado extensa.
- Mantener un tono profesional pero amigable.

Quiero que alguien que nunca haya visto Prism Motion pueda leer el documento y entender perfectamente de qué trata la aplicación, cómo utilizarla y qué puede aprender con ella.

Al mismo tiempo, un desarrollador debe poder identificar las tecnologías utilizadas, las APIs de animación, la estructura general del proyecto y dónde continuar trabajando.

---

## 19. Entrega final

Una vez terminada la documentación:

1. Crea o modifica `Documentacion.md`en la raíz del proyecto.
2. Organiza correctamente los títulos y subsecciones.
3. Verifica que todos los nombres de pantallas y laboratorios sean correctos.
4. Verifica que las APIs y librerías de animación documentadas existan realmente en el proyecto.
5. Verifica que las referencias a imágenes tengan nombres consistentes.
6. Comprueba que los enlaces internos y las referencias Markdown estén bien escritos.
7. Evita modificar código de la aplicación, ya que esta tarea es exclusivamente de documentación.
8. No elimines ni modifiques otros documentos existentes sin necesidad.
9. Al finalizar, proporciona un resumen de las secciones documentadas y menciona cualquier funcionalidad que hayas encontrado incompleta o que no pudieras verificar.

**Resultado esperado:** un manual de Prism Motion claro, agradable de leer, no demasiado extenso, útil tanto para usuarios como para desarrolladores y preparado para complementarse con capturas reales de todas las pantallas.
