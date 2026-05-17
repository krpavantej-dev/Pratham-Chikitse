package com.example.mindmatrix.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mindmatrix.R

data class Hospital(val name: String, val distance: String, val phone: String, val type: String)

@Composable
fun HospitalFinderScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val hospitals = listOf(
        Hospital("Victoria Hospital", "1.2 km", "+91 80 2670 1150", "Government Trauma Centre"),
        Hospital("Bowring & Lady Curzon Hospital", "3.5 km", "+91 80 2558 2235", "Government General Hospital"),
        Hospital("St. Martha's Hospital", "5.0 km", "+91 80 2227 5524", "Private Emergency Centre"),
        Hospital("Nimhans Emergency", "6.3 km", "+91 80 4611 5900", "Neurology & Trauma"),
        Hospital("Kidwai Memorial Institute", "7.8 km", "+91 80 2656 5711", "Cancer & Emergency")
    )

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFFF5F5F5))) {
        // ─── Gradient Header ──────────────────────────────────────────────
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(listOf(Color(0xFF0D47A1), Color(0xFF1565C0), Color(0xFF1976D2)))
                )
                .padding(top = 44.dp, start = 8.dp, end = 16.dp, bottom = 20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
                Spacer(Modifier.width(4.dp))
                Column {
                    Text(
                        "🏥  " + stringResource(R.string.hospital_finder),
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(
                        "Tap any card to call directly",
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 12.sp
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Offline disclaimer
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("📡", fontSize = 24.sp)
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text(
                                "Simulated Offline List",
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF0D47A1),
                                fontSize = 14.sp
                            )
                            Text(
                                "Tap 'Call Now' to dial a hospital directly.",
                                fontSize = 12.sp,
                                color = Color(0xFF1565C0)
                            )
                        }
                    }
                }
            }

            items(hospitals.withIndex().toList()) { (index, hospital) ->
                HospitalCard(hospital = hospital, rank = index + 1) { phone ->
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
                    context.startActivity(intent)
                }
            }
        }
    }
}

@Composable
fun HospitalCard(hospital: Hospital, rank: Int, onCall: (String) -> Unit) {
    val isNearest = rank == 1

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = if (isNearest) 8.dp else 4.dp, shape = RoundedCornerShape(18.dp)),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isNearest) Color(0xFFFFF8E1) else Color.White
        ),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Rank circle
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(
                            if (isNearest) Color(0xFFFF6F00) else Color(0xFF0D47A1)
                        )
                ) {
                    Text(
                        "#$rank",
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 13.sp
                    )
                }
                Spacer(Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            hospital.name,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF212121)
                        )
                        if (isNearest) {
                            Spacer(Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color(0xFFFF6F00))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    "NEAREST",
                                    color = Color.White,
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                        }
                    }
                    Text(
                        hospital.type,
                        fontSize = 12.sp,
                        color = Color(0xFF757575)
                    )
                }
            }

            Spacer(Modifier.height(12.dp))
            HorizontalDivider(color = Color(0xFFEEEEEE))
            Spacer(Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Distance chip
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFE3F2FD))
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Icon(
                        Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Color(0xFF1565C0),
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        hospital.distance,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1565C0)
                    )
                }

                // Phone number
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Call,
                        contentDescription = null,
                        tint = Color(0xFF757575),
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        hospital.phone,
                        fontSize = 12.sp,
                        color = Color(0xFF757575)
                    )
                }

                // Call button
                Button(
                    onClick = { onCall(hospital.phone) },
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isNearest) Color(0xFFFF6F00) else Color(0xFF1B5E20)
                    )
                ) {
                    Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(14.dp))
                    Spacer(Modifier.width(4.dp))
                    Text(
                        stringResource(R.string.call_now),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
