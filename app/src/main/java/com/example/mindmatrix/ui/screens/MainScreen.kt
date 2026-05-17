package com.example.mindmatrix.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mindmatrix.R
import com.example.mindmatrix.data.EmergencyCase
import com.example.mindmatrix.data.EmergencyDataProvider
import com.example.mindmatrix.ui.theme.MindmatrixTheme

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MindmatrixTheme { MainScreen(onEmergencyClick = {}, onHospitalFinderClick = {}) }
}

@Composable
fun MainScreen(
    onEmergencyClick: (Int) -> Unit,
    onHospitalFinderClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize().background(Color(0xFFF5F5F5))) {
        Column(modifier = Modifier.fillMaxSize()) {
            // ─── Gradient Header ───────────────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color(0xFFB71C1C), Color(0xFFD32F2F), Color(0xFFE53935))
                        )
                    )
                    .padding(top = 44.dp, bottom = 20.dp, start = 20.dp, end = 20.dp)
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.app_name),
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 0.5.sp
                    )
                    Text(
                        text = stringResource(R.string.app_tagline),
                        color = Color.White.copy(alpha = 0.85f),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(Modifier.height(16.dp))
                    // Offline badge
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.White.copy(alpha = 0.2f))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Icon(
                            Icons.Default.WifiOff,
                            contentDescription = null,
                            tint = Color(0xFF80FF80),
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            stringResource(R.string.offline_ready),
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            // ─── Section label ─────────────────────────────────────────────
            Text(
                text = stringResource(R.string.select_emergency),
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF616161),
                modifier = Modifier.padding(start = 16.dp, top = 14.dp, bottom = 2.dp)
            )

            // ─── Grid ──────────────────────────────────────────────────────
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(EmergencyDataProvider.emergencies) { emergency ->
                    EmergencyTile(
                        emergency = emergency,
                        title = stringResource(emergency.titleRes),
                        onClick = { onEmergencyClick(emergency.id) }
                    )
                }
            }

            // ─── Bottom Action Buttons ─────────────────────────────────────
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 14.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Hospital finder
                Button(
                    onClick = onHospitalFinderClick,
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D47A1))
                ) {
                    Icon(Icons.Default.LocalHospital, contentDescription = null, modifier = Modifier.size(20.dp))
                    Spacer(Modifier.width(8.dp))
                    Text(stringResource(R.string.hospital_finder), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }

                // Ambulance SOS with pulse animation
                PulsingSOSButton()
            }
        }
    }
}

@Composable
fun PulsingSOSButton() {
    val infiniteTransition = rememberInfiniteTransition(label = "sos_pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.04f,
        animationSpec = infiniteRepeatable(
            animation = tween(700, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        ),
        label = "sos_scale"
    )

    Button(
        onClick = { /* dial 108 */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .scale(scale),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC62828)),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
    ) {
        Icon(Icons.Default.Phone, contentDescription = null, modifier = Modifier.size(22.dp))
        Spacer(Modifier.width(8.dp))
        Text(stringResource(R.string.call_ambulance), fontSize = 17.sp, fontWeight = FontWeight.ExtraBold)
    }
}

@Composable
fun EmergencyTile(emergency: EmergencyCase, title: String, onClick: () -> Unit) {
    val gradient = Brush.linearGradient(listOf(emergency.gradientStart, emergency.gradientEnd))

    // Animated press scale
    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.94f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "tile_scale"
    )

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .scale(scale)
            .shadow(elevation = 6.dp, shape = RoundedCornerShape(18.dp))
            .clip(RoundedCornerShape(18.dp))
            .background(gradient)
            .clickable {
                pressed = true
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = emergency.emoji,
                fontSize = 36.sp,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(8.dp))
            // Strip emoji from title string (it's already in the emoji field)
            val cleanTitle = title.replace(Regex("^\\S+\\s"), "")
            Text(
                text = cleanTitle,
                textAlign = TextAlign.Center,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 17.sp,
                color = Color.White
            )
        }
    }
}
