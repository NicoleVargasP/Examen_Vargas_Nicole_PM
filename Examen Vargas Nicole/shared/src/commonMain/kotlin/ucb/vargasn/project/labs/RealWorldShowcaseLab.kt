package ucb.vargasn.project.labs

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ucb.vargasn.project.core.components.GlassCard
import ucb.vargasn.project.core.components.GlowButton
import ucb.vargasn.project.core.model.LocalMotionSettings
import ucb.vargasn.project.theme.AccentMagenta
import ucb.vargasn.project.theme.AppTheme
import ucb.vargasn.project.theme.PrimaryPurple

data class StoreProductItem(
    val id: String,
    val name: String,
    val category: String,
    val price: Double,
    val rating: Double,
    val description: String
)

@Composable
fun RealWorldShowcaseLabContent(modifier: Modifier = Modifier, resetToken: Int = 0) {
    val motionSettings = LocalMotionSettings.current
    val coroutineScope = rememberCoroutineScope()

    var animatedMode by remember(resetToken) { mutableStateOf(true) }
    var selectedCategory by remember(resetToken) { mutableStateOf("Todos") }
    var shippingMethod by remember(resetToken) { mutableStateOf("Estándar") }
    var couponApplied by remember(resetToken) { mutableStateOf(false) }

    val products = remember(resetToken) {
        listOf(
            StoreProductItem("prod_1", "Auriculares Pro X", "Audio", 350.0, 4.9, "Audio espacial inmersivo, cancelación activa de ruido y 30h de batería."),
            StoreProductItem("prod_2", "Smartwatch Ultra", "Wearables", 480.0, 4.8, "Pantalla AMOLED, GPS integrado, monitoreo cardíaco 24/7."),
            StoreProductItem("prod_3", "Teclado Mecánico RGB", "Gaming", 290.0, 4.7, "Switches ópticos intercambiables y retroiluminación personalizable."),
            StoreProductItem("prod_4", "Altavoz Nebula", "Audio", 210.0, 4.6, "Sonido 360 resistente al agua con 20h de reproducción.")
        )
    }

    val favorites = remember(resetToken) { mutableStateMapOf<String, Boolean>() }
    val quantities = remember(resetToken) { mutableStateMapOf<String, Int>() }
    val expandedCards = remember(resetToken) { mutableStateMapOf<String, Boolean>() }
    val addedAnimations = remember(resetToken) { mutableStateMapOf<String, Boolean>() }

    var notificationMessage by remember(resetToken) { mutableStateOf<String?>(null) }
    var cartExpanded by remember(resetToken) { mutableStateOf(false) }
    var checkoutDialog by remember(resetToken) { mutableStateOf(false) }
    var isLoadingShimmer by remember(resetToken) { mutableStateOf(false) }
    var currentCarouselPage by remember(resetToken) { mutableStateOf(0) }

    val filteredProducts = remember(selectedCategory, products) {
        if (selectedCategory == "Todos") products else products.filter { it.category == selectedCategory }
    }

    val totalItemsCount = quantities.values.sum()
    val subtotal = products.sumOf { prod -> (quantities[prod.id] ?: 0) * prod.price }
    val shippingFee = if (shippingMethod == "Exprés") 25.0 else 0.0
    val discountVal = if (couponApplied) 50.0 else 0.0
    val finalTotal = (subtotal + shippingFee - discountVal).coerceAtLeast(0.0)

    val animTotalItems by animateIntAsState(
        targetValue = totalItemsCount,
        animationSpec = if (animatedMode && !motionSettings.reduceMotion) spring(dampingRatio = 0.4f, stiffness = 400f) else snap(),
        label = "animTotalItems"
    )

    // Animation 8: Shimmer loading alpha
    val infiniteTransition = rememberInfiniteTransition(label = "shimmerTransition")
    val shimmerAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "shimmerAlpha"
    )

    // Animation 5: Carousel banner pulse
    val bannerScale by infiniteTransition.animateFloat(
        initialValue = 0.98f,
        targetValue = 1.02f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "bannerScale"
    )

    // Auto-dismiss notification
    LaunchedEffect(notificationMessage) {
        if (notificationMessage != null) {
            delay(2200)
            notificationMessage = null
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Interactive Mode Selector Banner (Con vs Sin animaciones)
        GlassCard(
            modifier = Modifier.fillMaxWidth(),
            cornerRadius = 22.dp
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Modo de Experiencia",
                    style = AppTheme.typography.label,
                    color = AppTheme.colors.primary
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(20.dp))
                            .background(if (!animatedMode) PrimaryPurple else AppTheme.colors.surface)
                            .clickable { animatedMode = false }
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                            Text("Sin animaciones", style = AppTheme.typography.label, color = Color.White)
                        }
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(20.dp))
                            .background(
                                if (animatedMode) Brush.linearGradient(colors = listOf(PrimaryPurple, AccentMagenta))
                                else Brush.linearGradient(colors = listOf(AppTheme.colors.surface, AppTheme.colors.surface))
                            )
                            .clickable { animatedMode = true }
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                            Text("Con animaciones", style = AppTheme.typography.label, color = Color.White)
                        }
                    }
                }
            }
        }

        // 2. Notification Toast Banner
        AnimatedVisibility(
            visible = notificationMessage != null,
            enter = if (animatedMode && !motionSettings.reduceMotion) fadeIn(tween(300)) + slideInVertically(tween(300)) { -it } else fadeIn(tween(0)),
            exit = if (animatedMode && !motionSettings.reduceMotion) fadeOut(tween(200)) + slideOutVertically(tween(200)) { -it } else fadeOut(tween(0))
        ) {
            notificationMessage?.let { msg ->
                Surface(
                    color = AppTheme.colors.surface,
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(1.dp, AppTheme.colors.secondary),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = Color(0xFF10B981))
                            Text(msg, style = AppTheme.typography.label, color = AppTheme.colors.onBackground)
                        }
                        IconButton(onClick = { notificationMessage = null }, modifier = Modifier.size(24.dp)) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = null, tint = AppTheme.colors.onBackground.copy(alpha = 0.7f))
                        }
                    }
                }
            }
        }

        // 3. Featured Promo Carousel Banner (Animation 10)
        GlassCard(modifier = Modifier.fillMaxWidth(), cornerRadius = 20.dp) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text("Destacados de la Semana", style = AppTheme.typography.label, color = AppTheme.colors.primary)
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        listOf(0, 1).forEach { pageIdx ->
                            Box(
                                modifier = Modifier
                                    .size(if (currentCarouselPage == pageIdx) 16.dp else 8.dp, 8.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(if (currentCarouselPage == pageIdx) AppTheme.colors.secondary else AppTheme.colors.onBackground.copy(alpha = 0.3f))
                                    .clickable { currentCarouselPage = pageIdx }
                            )
                        }
                    }
                }

                Box(modifier = Modifier.scale(if (animatedMode && !motionSettings.reduceMotion) bannerScale else 1f)) {
                    if (animatedMode && !motionSettings.reduceMotion) {
                        AnimatedContent(
                            targetState = currentCarouselPage,
                            transitionSpec = { fadeIn(tween(300)) togetherWith fadeOut(tween(300)) },
                            label = "carousel"
                        ) { page ->
                            CarouselBannerPage(page)
                        }
                    } else {
                        CarouselBannerPage(currentCarouselPage)
                    }
                }
            }
        }

        // 4. Category Filter Chips
        GlassCard(modifier = Modifier.fillMaxWidth(), cornerRadius = 20.dp) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Categorías", style = AppTheme.typography.label, color = AppTheme.colors.primary)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    listOf("Todos", "Audio", "Wearables", "Gaming").forEach { cat ->
                        val isSelected = selectedCategory == cat
                        val chipColor by animateColorAsState(
                            targetValue = if (isSelected) PrimaryPurple else AppTheme.colors.surface,
                            animationSpec = if (animatedMode && !motionSettings.reduceMotion) tween(300) else snap(),
                            label = "chip"
                        )
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(12.dp))
                                .background(chipColor)
                                .clickable {
                                    selectedCategory = cat
                                    isLoadingShimmer = true
                                    coroutineScope.launch {
                                        delay(motionSettings.scaleMillis(350))
                                        isLoadingShimmer = false
                                    }
                                }
                                .padding(vertical = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(cat, style = AppTheme.typography.label, color = if (isSelected) Color.White else AppTheme.colors.onBackground)
                        }
                    }
                }
            }
        }

        // 5. Product Catalog
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Catálogo de Productos", style = AppTheme.typography.heading, color = AppTheme.colors.onBackground)

            if (isLoadingShimmer) {
                GlassCard(modifier = Modifier.fillMaxWidth().height(110.dp), cornerRadius = 20.dp) {
                    Box(modifier = Modifier.fillMaxSize().background(PrimaryPurple.copy(alpha = shimmerAlpha)), contentAlignment = Alignment.Center) {
                        Text("Actualizando catálogo...", style = AppTheme.typography.label, color = Color.White)
                    }
                }
            } else {
                filteredProducts.forEach { product ->
                    val isFav = favorites[product.id] == true
                    val qty = quantities[product.id] ?: 0
                    val isExpanded = expandedCards[product.id] == true
                    val isAdded = addedAnimations[product.id] == true

                    val favScale by animateFloatAsState(
                        targetValue = if (isFav) 1.35f else 1f,
                        animationSpec = if (animatedMode && !motionSettings.reduceMotion) spring(dampingRatio = 0.25f, stiffness = 500f) else snap(),
                        label = "favScale"
                    )

                    val btnScale by animateFloatAsState(
                        targetValue = if (isAdded) 0.92f else 1f,
                        animationSpec = if (animatedMode && !motionSettings.reduceMotion) spring(dampingRatio = 0.3f, stiffness = 500f) else snap(),
                        label = "btnScale"
                    )

                    GlassCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .ifElse(animatedMode && !motionSettings.reduceMotion, Modifier.animateContentSize(tween(300))),
                        cornerRadius = 20.dp
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(54.dp)
                                            .clip(RoundedCornerShape(12.dp))
                                            .background(PrimaryPurple.copy(alpha = 0.3f)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null, tint = Color.White, modifier = Modifier.size(24.dp))
                                    }
                                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                        Text(product.name, style = AppTheme.typography.heading.copy(fontSize = 16.sp), color = AppTheme.colors.onBackground, maxLines = 1)
                                        Text("Bs. ${product.price.toInt()} • ⭐ ${product.rating}", style = AppTheme.typography.label, color = AppTheme.colors.onBackground.copy(alpha = 0.7f))
                                    }
                                }

                                // Independent Favorite Heart Button
                                IconButton(
                                    onClick = { favorites[product.id] = !isFav },
                                    modifier = Modifier.size(36.dp).scale(favScale)
                                ) {
                                    Icon(
                                        imageVector = if (isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                        contentDescription = "Favorito",
                                        tint = if (isFav) Color(0xFFEF4444) else AppTheme.colors.onBackground,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                TextButton(onClick = { expandedCards[product.id] = !isExpanded }) {
                                    Text(if (isExpanded) "Ocultar descripción" else "Ver descripción", color = AppTheme.colors.primary)
                                }

                                Box(modifier = Modifier.scale(btnScale)) {
                                    Button(
                                        onClick = {
                                            addedAnimations[product.id] = true
                                            quantities[product.id] = qty + 1
                                            notificationMessage = "¡${product.name} agregado al carrito!"
                                            coroutineScope.launch {
                                                delay(motionSettings.scaleMillis(150))
                                                addedAnimations[product.id] = false
                                            }
                                        },
                                        colors = ButtonDefaults.buttonColors(containerColor = if (qty > 0) Color(0xFF10B981) else PrimaryPurple),
                                        shape = RoundedCornerShape(12.dp),
                                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
                                    ) {
                                        Text(if (qty > 0) "Añadidos ($qty)" else "Agregar +", style = AppTheme.typography.label, color = Color.White)
                                    }
                                }
                            }

                            if (isExpanded) {
                                if (animatedMode && !motionSettings.reduceMotion) {
                                    AnimatedVisibility(visible = true, enter = fadeIn() + expandVertically()) {
                                        Text(product.description, style = AppTheme.typography.body, color = AppTheme.colors.onBackground.copy(alpha = 0.8f))
                                    }
                                } else {
                                    Text(product.description, style = AppTheme.typography.body, color = AppTheme.colors.onBackground.copy(alpha = 0.8f))
                                }
                            }
                        }
                    }
                }
            }
        }

        // 6. Discount Coupon
        GlassCard(modifier = Modifier.fillMaxWidth(), cornerRadius = 20.dp) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .ifElse(animatedMode && !motionSettings.reduceMotion, Modifier.animateContentSize(tween(300))),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.LocalOffer, contentDescription = null, tint = AppTheme.colors.secondary)
                        Text("¿Tienes un cupón de descuento?", style = AppTheme.typography.label, color = AppTheme.colors.onBackground)
                    }
                    TextButton(onClick = { couponApplied = !couponApplied }) {
                        Text(if (couponApplied) "Quitar" else "Aplicar PRISM50", color = AppTheme.colors.primary)
                    }
                }

                if (couponApplied) {
                    if (animatedMode && !motionSettings.reduceMotion) {
                        AnimatedVisibility(visible = true, enter = fadeIn() + expandVertically()) {
                            Surface(color = Color(0xFF10B981).copy(alpha = 0.2f), shape = RoundedCornerShape(10.dp), modifier = Modifier.fillMaxWidth()) {
                                Row(modifier = Modifier.padding(10.dp), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                                    Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = Color(0xFF10B981), modifier = Modifier.size(16.dp))
                                    Text("Cupón PRISM50 aplicado con éxito (-Bs. 50)", style = AppTheme.typography.label, color = Color(0xFF10B981))
                                }
                            }
                        }
                    } else {
                        Surface(color = Color(0xFF10B981).copy(alpha = 0.2f), shape = RoundedCornerShape(10.dp), modifier = Modifier.fillMaxWidth()) {
                            Row(modifier = Modifier.padding(10.dp), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = Color(0xFF10B981), modifier = Modifier.size(16.dp))
                                Text("Cupón PRISM50 aplicado con éxito (-Bs. 50)", style = AppTheme.typography.label, color = Color(0xFF10B981))
                            }
                        }
                    }
                }
            }
        }

        // 7. Cart Summary Card
        GlassCard(
            modifier = Modifier.fillMaxWidth(),
            cornerRadius = 24.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .ifElse(animatedMode && !motionSettings.reduceMotion, Modifier.animateContentSize(tween(300))),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null, tint = AppTheme.colors.primary)
                        Text("Resumen del Pedido", style = AppTheme.typography.heading, color = AppTheme.colors.onBackground)
                    }
                    Surface(
                        color = AppTheme.colors.primary.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        val animTotalItems by animateIntAsState(
                            targetValue = totalItemsCount,
                            animationSpec = if (animatedMode && !motionSettings.reduceMotion) spring(dampingRatio = 0.4f) else snap(),
                            label = "totalItems"
                        )
                        Text(
                            text = "$animTotalItems ítems",
                            style = AppTheme.typography.label,
                            color = AppTheme.colors.primary,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Total a Pagar", style = AppTheme.typography.heading, color = AppTheme.colors.onBackground)
                    if (animatedMode && !motionSettings.reduceMotion) {
                        AnimatedContent(
                            targetState = finalTotal,
                            transitionSpec = { (scaleIn(tween(200)) + fadeIn(tween(200))) togetherWith (scaleOut(tween(200)) + fadeOut(tween(200))) },
                            label = "subtotal"
                        ) { sub ->
                            Text("Bs. ${sub.toInt()}", style = AppTheme.typography.heading.copy(fontSize = 20.sp), color = AppTheme.colors.secondary)
                        }
                    } else {
                        Text("Bs. ${finalTotal.toInt()}", style = AppTheme.typography.heading.copy(fontSize = 20.sp), color = AppTheme.colors.secondary)
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(AppTheme.colors.surface.copy(alpha = 0.6f))
                        .clickable { cartExpanded = !cartExpanded }
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(if (cartExpanded) "Ocultar detalles" else "Ver productos seleccionados", style = AppTheme.typography.label, color = AppTheme.colors.primary)
                        Icon(
                            imageVector = if (cartExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            tint = AppTheme.colors.primary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                if (cartExpanded) {
                    if (animatedMode && !motionSettings.reduceMotion) {
                        AnimatedVisibility(visible = true, enter = fadeIn() + expandVertically()) {
                            CartItemsDetails(products, quantities, onUpdateQty = { prodId, delta ->
                                val cur = quantities[prodId] ?: 0
                                val next = (cur + delta).coerceAtLeast(0)
                                if (next == 0) quantities.remove(prodId) else quantities[prodId] = next
                            })
                        }
                    } else {
                        CartItemsDetails(products, quantities, onUpdateQty = { prodId, delta ->
                            val cur = quantities[prodId] ?: 0
                            val next = (cur + delta).coerceAtLeast(0)
                            if (next == 0) quantities.remove(prodId) else quantities[prodId] = next
                        })
                    }
                }

                GlowButton(
                    text = "Confirmar Compra",
                    onClick = { checkoutDialog = true }
                )
            }
        }

        // Checkout Success Dialog
        if (checkoutDialog) {
            AlertDialog(
                onDismissRequest = { checkoutDialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        checkoutDialog = false
                        quantities.clear()
                    }) {
                        Text("Aceptar", color = AppTheme.colors.primary)
                    }
                },
                title = { Text("¡Compra Exitosa!", style = AppTheme.typography.heading) },
                text = { Text("Su pedido ha sido procesado correctamente con estados independientes y fluidos.", style = AppTheme.typography.body) },
                containerColor = AppTheme.colors.surface
            )
        }

        // Reset Demo Button
        GlassCard(modifier = Modifier.fillMaxWidth(), cornerRadius = 24.dp) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        quantities.clear()
                        favorites.clear()
                        expandedCards.clear()
                        notificationMessage = null
                        cartExpanded = false
                        couponApplied = false
                        shippingMethod = "Estándar"
                    }
                    .padding(vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = null, tint = AppTheme.colors.secondary, modifier = Modifier.size(18.dp))
                    Text("Reiniciar demostración", style = AppTheme.typography.label, color = AppTheme.colors.secondary)
                }
            }
        }
    }
}

@Composable
private fun CarouselBannerPage(page: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Brush.linearGradient(colors = listOf(PrimaryPurple, AccentMagenta)))
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = if (page == 0) "Lanzamiento Exclusivo Pro X" else "Descuento de Temporada PRISM50",
                style = AppTheme.typography.heading.copy(fontSize = 18.sp),
                color = Color.White
            )
            Text(
                text = if (page == 0) "Tecnología de punta con audio espacial." else "Ahorra Bs. 50 en tu primera compra.",
                style = AppTheme.typography.label,
                color = Color.White.copy(alpha = 0.9f)
            )
        }
    }
}

@Composable
private fun CartItemsDetails(
    items: List<StoreProductItem>,
    quantities: Map<String, Int>,
    onUpdateQty: (String, Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black.copy(alpha = 0.15f), RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val activeItems = items.filter { (quantities[it.id] ?: 0) > 0 }
        if (activeItems.isEmpty()) {
            Text("El carrito está vacío", style = AppTheme.typography.label, color = AppTheme.colors.onBackground.copy(alpha = 0.7f))
        } else {
            activeItems.forEach { item ->
                val qty = quantities[item.id] ?: 0
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(item.name, style = AppTheme.typography.label, color = AppTheme.colors.onBackground)
                        Text("Bs. ${item.price.toInt()} x $qty", style = AppTheme.typography.label.copy(fontSize = 11.sp), color = AppTheme.colors.onBackground.copy(alpha = 0.7f))
                    }
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        IconButton(onClick = { onUpdateQty(item.id, -1) }, modifier = Modifier.size(32.dp).background(AppTheme.colors.surface, CircleShape)) {
                            Text("-", color = AppTheme.colors.onBackground)
                        }
                        Box(modifier = Modifier.width(28.dp), contentAlignment = Alignment.Center) {
                            Text("$qty", style = AppTheme.typography.heading.copy(fontSize = 16.sp), color = AppTheme.colors.onBackground)
                        }
                        IconButton(onClick = { onUpdateQty(item.id, 1) }, modifier = Modifier.size(32.dp).background(AppTheme.colors.primary, CircleShape)) {
                            Text("+", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}

fun Modifier.ifElse(condition: Boolean, modifier: Modifier): Modifier =
    if (condition) this.then(modifier) else this
