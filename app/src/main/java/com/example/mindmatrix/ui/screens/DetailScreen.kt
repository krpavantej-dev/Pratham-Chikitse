package com.example.mindmatrix.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mindmatrix.R
import com.example.mindmatrix.audio.DummyTTSManager
import com.example.mindmatrix.audio.ITTSManager
import com.example.mindmatrix.audio.TTSManager
import com.example.mindmatrix.data.EmergencyDataProvider
import com.example.mindmatrix.ui.theme.MindmatrixTheme
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    MindmatrixTheme { DetailScreen(emergencyId = 1, onBack = {}, isPreview = true) }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    emergencyId: Int,
    onBack: () -> Unit,
    isPreview: Boolean = false
) {
    val emergency = EmergencyDataProvider.emergencies.find { it.id == emergencyId } ?: return
    val context = LocalContext.current
    val ttsManager: ITTSManager = remember { if (isPreview) DummyTTSManager() else TTSManager(context) }
    var isAudioMode by remember { mutableStateOf(false) }
    val pagerState = rememberPagerState(pageCount = { emergency.steps.size })
    val scope = rememberCoroutineScope()
    val totalSteps = emergency.steps.size

    DisposableEffect(Unit) { onDispose { ttsManager.shutdown() } }

    val gradient = Brush.linearGradient(listOf(emergency.gradientStart, emergency.gradientEnd))

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(gradient)
                    .padding(top = 40.dp, bottom = 0.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = onBack) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                emergency.emoji + "  " + stringResource(emergency.titleRes)
                                    .replace(Regex("^\\S+\\s"), ""),
                                color = Color.White,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp,
                                maxLines = 1
                            )
                            Text(
                                "${stringResource(R.string.step_label)} ${pagerState.currentPage + 1} ${stringResource(R.string.of_label)} $totalSteps",
                                color = Color.White.copy(alpha = 0.85f),
                                fontSize = 13.sp
                            )
                        }
                    }

                    // Segmented progress bar
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 12.dp),
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        repeat(totalSteps) { i ->
                            val done = i < pagerState.currentPage
                            val current = i == pagerState.currentPage
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(5.dp)
                                    .clip(RoundedCornerShape(3.dp))
                                    .background(
                                        when {
                                            done -> Color.White
                                            current -> Color.White.copy(alpha = 0.75f)
                                            else -> Color.White.copy(alpha = 0.25f)
                                        }
                                    )
                            )
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .padding(paddingValues)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                val step = emergency.steps[page]
                val instruction = stringResource(step.instructionRes)

                LaunchedEffect(page, isAudioMode) {
                    if (isAudioMode) ttsManager.speak(instruction)
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Step bubble + number
                    Box(contentAlignment = Alignment.Center) {
                        Box(
                            modifier = Modifier
                                .size(72.dp)
                                .clip(CircleShape)
                                .background(gradient)
                        )
                        Text(
                            "${page + 1}",
                            color = Color.White,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }

                    Spacer(Modifier.height(14.dp))

                    // Instruction card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(elevation = 4.dp, shape = RoundedCornerShape(18.dp)),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(0.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .offset(y = 9.dp)
                                    .clip(CircleShape)
                                    .background(emergency.gradientStart)
                            )
                            Spacer(Modifier.width(12.dp))
                            Text(
                                text = instruction,
                                fontSize = 18.sp,
                                lineHeight = 27.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF212121)
                            )
                        }
                    }

                    Spacer(Modifier.height(14.dp))

                    // Do's
                    if (step.dosRes != null) {
                        EnhancedInfoCard(
                            title = stringResource(R.string.dos),
                            content = stringResource(step.dosRes),
                            bgColor = Color(0xFFE8F5E9),
                            accentColor = Color(0xFF2E7D32),
                            icon = "✅"
                        )
                    }

                    // Don'ts
                    if (step.dontsRes != null) {
                        Spacer(Modifier.height(10.dp))
                        EnhancedInfoCard(
                            title = stringResource(R.string.donts),
                            content = stringResource(step.dontsRes),
                            bgColor = Color(0xFFFFEBEE),
                            accentColor = Color(0xFFC62828),
                            icon = "❌"
                        )
                    }

                    Spacer(Modifier.height(10.dp))
                }
            }

            // ─── Navigation row ────────────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Prev
                OutlinedButton(
                    onClick = {
                        if (pagerState.currentPage > 0) {
                            scope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) }
                        }
                    },
                    enabled = pagerState.currentPage > 0,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.height(46.dp)
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("Prev", fontWeight = FontWeight.SemiBold)
                }

                // Dot indicators
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    repeat(totalSteps) { i ->
                        val selected = pagerState.currentPage == i
                        val dotSize by animateDpAsState(
                            targetValue = if (selected) 10.dp else 7.dp,
                            label = "dot"
                        )
                        Box(
                            modifier = Modifier
                                .size(dotSize)
                                .clip(CircleShape)
                                .background(
                                    if (selected) emergency.gradientStart
                                    else emergency.gradientStart.copy(alpha = 0.3f)
                                )
                        )
                    }
                }

                // Next
                Button(
                    onClick = {
                        if (pagerState.currentPage < totalSteps - 1) {
                            scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                        }
                    },
                    enabled = pagerState.currentPage < totalSteps - 1,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = emergency.gradientStart),
                    modifier = Modifier.height(46.dp)
                ) {
                    Text("Next", fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.width(4.dp))
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(18.dp))
                }
            }

            // ─── Audio toggle button ───────────────────────────────────────
            Button(
                onClick = {
                    isAudioMode = !isAudioMode
                    if (!isAudioMode) ttsManager.stop()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .height(58.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isAudioMode) Color(0xFFC62828) else Color(0xFF1565C0)
                ),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(4.dp)
            ) {
                Icon(
                    imageVector = if (isAudioMode) Icons.Default.Stop else Icons.Default.PlayArrow,
                    contentDescription = null,
                    modifier = Modifier.size(26.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = if (isAudioMode) stringResource(R.string.audio_stop)
                           else stringResource(R.string.audio_mode),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}

@Composable
fun EnhancedInfoCard(
    title: String,
    content: String,
    bgColor: Color,
    accentColor: Color,
    icon: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(icon, fontSize = 18.sp)
                Spacer(Modifier.width(6.dp))
                Text(
                    title,
                    fontWeight = FontWeight.ExtraBold,
                    color = accentColor,
                    fontSize = 15.sp
                )
            }
            Spacer(Modifier.height(6.dp))
            HorizontalDivider(thickness = 1.dp, color = accentColor.copy(alpha = 0.2f))
            Spacer(Modifier.height(8.dp))
            Text(
                content,
                color = accentColor.copy(alpha = 0.9f),
                fontSize = 14.sp,
                lineHeight = 21.sp
            )
        }
    }
}

// Keep old InfoCard for any existing references (now delegates)
@Composable
fun InfoCard(title: String, content: String, bgColor: Color, textColor: Color) {
    EnhancedInfoCard(
        title = title,
        content = content,
        bgColor = bgColor,
        accentColor = textColor,
        icon = if (textColor == Color(0xFF1B5E20)) "✅" else "❌"
    )
}
