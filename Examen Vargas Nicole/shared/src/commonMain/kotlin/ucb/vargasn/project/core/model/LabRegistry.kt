package ucb.vargasn.project.core.model

import ucb.vargasn.project.labs.CanvasPlaygroundLabContent
import ucb.vargasn.project.labs.ContentSwapLabContent
import ucb.vargasn.project.labs.EasingLabContent
import ucb.vargasn.project.labs.GesturePhysicsLabContent
import ucb.vargasn.project.labs.InfiniteLoopsLabContent
import ucb.vargasn.project.labs.LayoutMagicLabContent
import ucb.vargasn.project.labs.RealWorldShowcaseLabContent
import ucb.vargasn.project.labs.SequencingStaggerLabContent
import ucb.vargasn.project.labs.SharedElementsLabContent
import ucb.vargasn.project.labs.SpringPlaygroundLabContent
import ucb.vargasn.project.labs.TextMotionLabContent
import ucb.vargasn.project.labs.TransitionStudioLabContent
import ucb.vargasn.project.labs.ValueMorphLabContent
import ucb.vargasn.project.labs.VisibilityMixerLabContent

object LabRegistry {
    val labs = listOf(
        Lab(
            id = "value-morph",
            title = "Value Morph",
            subtitle = "animate*AsState (Color, Dp, Float, Size, Offset)",
            description = "Utiliza animate*AsState para animar cambios de valores discretos (Color, Dp, Float, Size) de forma suave y automática cuando el estado cambia.",
            category = LabCategory.BASICS,
            accentColorHex = "8B5CF6"
        ) { modifier, resetToken ->
            ValueMorphLabContent(modifier, resetToken)
        },
        Lab(
            id = "spring-playground",
            title = "Spring Playground",
            subtitle = "Damping ratio, stiffness & physics comparison",
            description = "Simula física de resortes mediante spring(), permitiendo configurar la razón de amortiguación (rebote) y la rigidez (velocidad de retorno).",
            category = LabCategory.BASICS,
            accentColorHex = "E879F9"
        ) { modifier, resetToken ->
            SpringPlaygroundLabContent(modifier)
        },
        Lab(
            id = "easing-lab",
            title = "Easing Lab",
            subtitle = "CubicBezierEasing curve preview",
            description = "Explora curvas de aceleración (CubicBezierEasing, FastOutSlowInEasing) que determinan cómo cambia la velocidad de una animación a lo largo del tiempo.",
            category = LabCategory.BASICS,
            accentColorHex = "22D3EE"
        ) { modifier, resetToken ->
            EasingLabContent(modifier)
        },
        Lab(
            id = "visibility-mixer",
            title = "Visibility Mixer",
            subtitle = "AnimatedVisibility enter/exit builder",
            description = "Controla la aparición y desaparición de elementos usando AnimatedVisibility con transiciones combinadas de desvanecimiento, deslizamiento y escala.",
            category = LabCategory.MOTION,
            accentColorHex = "FB7185"
        ) { modifier, resetToken ->
            VisibilityMixerLabContent(modifier)
        },
        Lab(
            id = "content-swap",
            title = "Content Swap",
            subtitle = "AnimatedContent & Crossfade",
            description = "Permite transicionar entre diferentes contenidos dinámicos usando AnimatedContent o Crossfade con transiciones personalizadas.",
            category = LabCategory.MOTION,
            accentColorHex = "FBBF24"
        ) { modifier, resetToken ->
            ContentSwapLabContent(modifier)
        },
        Lab(
            id = "transition-studio",
            title = "Transition Studio",
            subtitle = "updateTransition multi-child animation",
            description = "Coordina múltiples propiedades simultáneamente (color, tamaño, rotación) asociadas a un estado común usando updateTransition.",
            category = LabCategory.MOTION,
            accentColorHex = "34D399"
        ) { modifier, resetToken ->
            TransitionStudioLabContent(modifier)
        },
        Lab(
            id = "infinite-loops",
            title = "Infinite Loops",
            subtitle = "rememberInfiniteTransition pulse & orbit",
            description = "Crea animaciones continuas en bucle mediante rememberInfiniteTransition para efectos de pulso, brillo u órbita.",
            category = LabCategory.MOTION,
            accentColorHex = "8B5CF6"
        ) { modifier, resetToken ->
            InfiniteLoopsLabContent(modifier)
        },
        Lab(
            id = "gesture-physics",
            title = "Gesture Physics",
            subtitle = "Drag, fling, decay & snap-back",
            description = "Integra gestos táctiles (pointerInput, detectDragGestures) con físicas de resorte para permitir arrastrar y soltar elementos con retorno elástico.",
            category = LabCategory.GESTURES,
            accentColorHex = "E879F9"
        ) { modifier, resetToken ->
            GesturePhysicsLabContent(modifier)
        },
        Lab(
            id = "sequencing-stagger",
            title = "Sequencing & Stagger",
            subtitle = "Animatable timeline & stagger",
            description = "Ejecuta animaciones en secuencia, en paralelo o con retraso escalonado (stagger) usando Animatable y corrutinas.",
            category = LabCategory.LAYOUT,
            accentColorHex = "22D3EE"
        ) { modifier, resetToken ->
            SequencingStaggerLabContent(modifier)
        },
        Lab(
            id = "layout-magic",
            title = "Layout Magic",
            subtitle = "animateContentSize & animateItem",
            description = "Anima cambios automáticos de tamaño y diseño usando animateContentSize para transiciones fluidas de contenedores y elementos.",
            category = LabCategory.LAYOUT,
            accentColorHex = "FB7185"
        ) { modifier, resetToken ->
            LayoutMagicLabContent(modifier)
        },
        Lab(
            id = "shared-elements",
            title = "Shared Elements",
            subtitle = "SharedTransitionLayout card → detail",
            description = "Simula transiciones de elementos compartidos entre tarjetas y vistas detalladas para mantener la orientación espacial del usuario.",
            category = LabCategory.LAYOUT,
            accentColorHex = "FBBF24"
        ) { modifier, resetToken ->
            SharedElementsLabContent(modifier)
        },
        Lab(
            id = "canvas-playground",
            title = "Canvas Playground",
            subtitle = "Animated paths & particle bursts",
            description = "Dibuja formas dinámicas y ondas fluidas en un Canvas animado fotograma a fotograma con el ciclo de renderizado de Compose.",
            category = LabCategory.CANVAS,
            accentColorHex = "34D399"
        ) { modifier, resetToken ->
            CanvasPlaygroundLabContent(modifier)
        },
        Lab(
            id = "text-motion",
            title = "Text Motion",
            subtitle = "Animated counter & stagger text",
            description = "Anima contadores numéricos y cambios de texto fluido usando animateIntAsState con tipografía dinámica.",
            category = LabCategory.CANVAS,
            accentColorHex = "8B5CF6"
        ) { modifier, resetToken ->
            TextMotionLabContent(modifier)
        },
        Lab(
            id = "real-world-showcase",
            title = "Ejemplo",
            subtitle = "Tienda E-Commerce",
            description = "Caso de uso comercial con selección independiente, gestión de carrito y animaciones nativas de Compose.",
            category = LabCategory.MOTION,
            accentColorHex = "E879F9"
        ) { modifier, resetToken ->
            RealWorldShowcaseLabContent(modifier, resetToken)
        }
    )
}
