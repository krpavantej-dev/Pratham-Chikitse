package com.example.mindmatrix.data

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.example.mindmatrix.R

data class EmergencyStep(
    @StringRes val instructionRes: Int,
    @StringRes val dosRes: Int? = null,
    @StringRes val dontsRes: Int? = null
)

data class EmergencyCase(
    val id: Int,
    @StringRes val titleRes: Int,
    val steps: List<EmergencyStep>,
    val emoji: String = "🚨",
    val gradientStart: Color = Color(0xFFD32F2F),
    val gradientEnd: Color = Color(0xFF7F0000)
)

object EmergencyDataProvider {
    val emergencies = listOf(
        EmergencyCase(
            id = 1, titleRes = R.string.emergency_snake_bite, emoji = "🐍",
            gradientStart = Color(0xFF43A047), gradientEnd = Color(0xFF1B5E20),
            steps = listOf(
                EmergencyStep(R.string.snake_step_1, R.string.snake_do_1, R.string.snake_dont_1),
                EmergencyStep(R.string.snake_step_2, R.string.snake_do_2, R.string.snake_dont_2),
                EmergencyStep(R.string.snake_step_3, R.string.snake_do_3, R.string.snake_dont_3)
            )
        ),
        EmergencyCase(
            id = 2, titleRes = R.string.emergency_heart_attack, emoji = "❤️",
            gradientStart = Color(0xFFE53935), gradientEnd = Color(0xFF7F0000),
            steps = listOf(
                EmergencyStep(R.string.heart_step_1, R.string.heart_do_1, R.string.heart_dont_1),
                EmergencyStep(R.string.heart_step_2, R.string.heart_do_2, R.string.heart_dont_2),
                EmergencyStep(R.string.heart_step_3, R.string.heart_do_3, R.string.heart_dont_3)
            )
        ),
        EmergencyCase(
            id = 3, titleRes = R.string.emergency_burn, emoji = "🔥",
            gradientStart = Color(0xFFFF7043), gradientEnd = Color(0xFFBF360C),
            steps = listOf(
                EmergencyStep(R.string.burn_step_1, R.string.burn_do_1, R.string.burn_dont_1),
                EmergencyStep(R.string.burn_step_2, R.string.burn_do_2, R.string.burn_dont_2),
                EmergencyStep(R.string.burn_step_3, R.string.burn_do_3, R.string.burn_dont_3)
            )
        ),
        EmergencyCase(
            id = 4, titleRes = R.string.emergency_choking, emoji = "😮",
            gradientStart = Color(0xFF5C6BC0), gradientEnd = Color(0xFF1A237E),
            steps = listOf(
                EmergencyStep(R.string.choke_step_1, R.string.choke_do_1, R.string.choke_dont_1),
                EmergencyStep(R.string.choke_step_2, R.string.choke_do_2, R.string.choke_dont_2),
                EmergencyStep(R.string.choke_step_3, R.string.choke_do_3, R.string.choke_dont_3)
            )
        ),
        EmergencyCase(
            id = 5, titleRes = R.string.emergency_fracture, emoji = "🦴",
            gradientStart = Color(0xFF78909C), gradientEnd = Color(0xFF263238),
            steps = listOf(
                EmergencyStep(R.string.fracture_step_1, R.string.fracture_do_1, R.string.fracture_dont_1),
                EmergencyStep(R.string.fracture_step_2, R.string.fracture_do_2, R.string.fracture_dont_2),
                EmergencyStep(R.string.fracture_step_3, R.string.fracture_do_3, R.string.fracture_dont_3)
            )
        ),
        EmergencyCase(
            id = 6, titleRes = R.string.emergency_drowning, emoji = "🌊",
            gradientStart = Color(0xFF29B6F6), gradientEnd = Color(0xFF0277BD),
            steps = listOf(
                EmergencyStep(R.string.drowning_step_1, R.string.drowning_do_1, R.string.drowning_dont_1),
                EmergencyStep(R.string.drowning_step_2, R.string.drowning_do_2, R.string.drowning_dont_2),
                EmergencyStep(R.string.drowning_step_3, R.string.drowning_do_3, R.string.drowning_dont_3)
            )
        ),
        EmergencyCase(
            id = 7, titleRes = R.string.emergency_electric_shock, emoji = "⚡",
            gradientStart = Color(0xFFFFCA28), gradientEnd = Color(0xFFE65100),
            steps = listOf(
                EmergencyStep(R.string.electric_step_1, R.string.electric_do_1, R.string.electric_dont_1),
                EmergencyStep(R.string.electric_step_2, R.string.electric_do_2, R.string.electric_dont_2),
                EmergencyStep(R.string.electric_step_3, R.string.electric_do_3, R.string.electric_dont_3)
            )
        ),
        EmergencyCase(
            id = 8, titleRes = R.string.emergency_seizure, emoji = "🧠",
            gradientStart = Color(0xFFAB47BC), gradientEnd = Color(0xFF6A1B9A),
            steps = listOf(
                EmergencyStep(R.string.seizure_step_1, R.string.seizure_do_1, R.string.seizure_dont_1),
                EmergencyStep(R.string.seizure_step_2, R.string.seizure_do_2, R.string.seizure_dont_2),
                EmergencyStep(R.string.seizure_step_3, R.string.seizure_do_3, R.string.seizure_dont_3)
            )
        ),
        EmergencyCase(
            id = 9, titleRes = R.string.emergency_poisoning, emoji = "☠️",
            gradientStart = Color(0xFF66BB6A), gradientEnd = Color(0xFF1B5E20),
            steps = listOf(
                EmergencyStep(R.string.poison_step_1, R.string.poison_do_1, R.string.poison_dont_1),
                EmergencyStep(R.string.poison_step_2, R.string.poison_do_2, R.string.poison_dont_2),
                EmergencyStep(R.string.poison_step_3, R.string.poison_do_3, R.string.poison_dont_3)
            )
        ),
        EmergencyCase(
            id = 10, titleRes = R.string.emergency_eye_injury, emoji = "👁️",
            gradientStart = Color(0xFF26C6DA), gradientEnd = Color(0xFF00695C),
            steps = listOf(
                EmergencyStep(R.string.eye_step_1, R.string.eye_do_1, R.string.eye_dont_1),
                EmergencyStep(R.string.eye_step_2, R.string.eye_do_2, R.string.eye_dont_2),
                EmergencyStep(R.string.eye_step_3, R.string.eye_do_3, R.string.eye_dont_3)
            )
        ),
        EmergencyCase(
            id = 11, titleRes = R.string.emergency_fever, emoji = "🌡️",
            gradientStart = Color(0xFFEF9A9A), gradientEnd = Color(0xFFC62828),
            steps = listOf(
                EmergencyStep(R.string.fever_step_1, R.string.fever_do_1, R.string.fever_dont_1),
                EmergencyStep(R.string.fever_step_2, R.string.fever_do_2, R.string.fever_dont_2),
                EmergencyStep(R.string.fever_step_3, R.string.fever_do_3, R.string.fever_dont_3)
            )
        ),
        EmergencyCase(
            id = 12, titleRes = R.string.emergency_asthma, emoji = "💨",
            gradientStart = Color(0xFF80DEEA), gradientEnd = Color(0xFF006064),
            steps = listOf(
                EmergencyStep(R.string.asthma_step_1, R.string.asthma_do_1, R.string.asthma_dont_1),
                EmergencyStep(R.string.asthma_step_2, R.string.asthma_do_2, R.string.asthma_dont_2),
                EmergencyStep(R.string.asthma_step_3, R.string.asthma_do_3, R.string.asthma_dont_3)
            )
        ),
        EmergencyCase(
            id = 13, titleRes = R.string.emergency_sprain, emoji = "🦵",
            gradientStart = Color(0xFFA5D6A7), gradientEnd = Color(0xFF388E3C),
            steps = listOf(
                EmergencyStep(R.string.sprain_step_1, R.string.sprain_do_1, R.string.sprain_dont_1),
                EmergencyStep(R.string.sprain_step_2, R.string.sprain_do_2, R.string.sprain_dont_2),
                EmergencyStep(R.string.sprain_step_3, R.string.sprain_do_3, R.string.sprain_dont_3)
            )
        ),
        EmergencyCase(
            id = 14, titleRes = R.string.emergency_nosebleed, emoji = "🩸",
            gradientStart = Color(0xFFEF5350), gradientEnd = Color(0xFF880E4F),
            steps = listOf(
                EmergencyStep(R.string.nose_step_1, R.string.nose_do_1, R.string.nose_dont_1),
                EmergencyStep(R.string.nose_step_2, R.string.nose_do_2, R.string.nose_dont_2),
                EmergencyStep(R.string.nose_step_3, R.string.nose_do_3, R.string.nose_dont_3)
            )
        ),
        EmergencyCase(
            id = 15, titleRes = R.string.emergency_diabetic, emoji = "💉",
            gradientStart = Color(0xFF42A5F5), gradientEnd = Color(0xFF0D47A1),
            steps = listOf(
                EmergencyStep(R.string.diabetic_step_1, R.string.diabetic_do_1, R.string.diabetic_dont_1),
                EmergencyStep(R.string.diabetic_step_2, R.string.diabetic_do_2, R.string.diabetic_dont_2),
                EmergencyStep(R.string.diabetic_step_3, R.string.diabetic_do_3, R.string.diabetic_dont_3)
            )
        ),
        EmergencyCase(
            id = 16, titleRes = R.string.emergency_head_injury, emoji = "🤕",
            gradientStart = Color(0xFFFFB74D), gradientEnd = Color(0xFFE65100),
            steps = listOf(
                EmergencyStep(R.string.head_step_1, R.string.head_do_1, R.string.head_dont_1),
                EmergencyStep(R.string.head_step_2, R.string.head_do_2, R.string.head_dont_2),
                EmergencyStep(R.string.head_step_3, R.string.head_do_3, R.string.head_dont_3)
            )
        ),
        EmergencyCase(
            id = 17, titleRes = R.string.emergency_allergic, emoji = "🚨",
            gradientStart = Color(0xFFEC407A), gradientEnd = Color(0xFF880E4F),
            steps = listOf(
                EmergencyStep(R.string.allergy_step_1, R.string.allergy_do_1, R.string.allergy_dont_1),
                EmergencyStep(R.string.allergy_step_2, R.string.allergy_do_2, R.string.allergy_dont_2),
                EmergencyStep(R.string.allergy_step_3, R.string.allergy_do_3, R.string.allergy_dont_3)
            )
        ),
        EmergencyCase(
            id = 18, titleRes = R.string.emergency_bleeding, emoji = "🩹",
            gradientStart = Color(0xFFEF5350), gradientEnd = Color(0xFF7F0000),
            steps = listOf(
                EmergencyStep(R.string.bleeding_step_1, R.string.bleeding_do_1, R.string.bleeding_dont_1),
                EmergencyStep(R.string.bleeding_step_2, R.string.bleeding_do_2, R.string.bleeding_dont_2),
                EmergencyStep(R.string.bleeding_step_3, R.string.bleeding_do_3, R.string.bleeding_dont_3)
            )
        ),
        EmergencyCase(
            id = 19, titleRes = R.string.emergency_heatstroke, emoji = "☀️",
            gradientStart = Color(0xFFFFD54F), gradientEnd = Color(0xFFFF6F00),
            steps = listOf(
                EmergencyStep(R.string.heat_step_1, R.string.heat_do_1, R.string.heat_dont_1),
                EmergencyStep(R.string.heat_step_2, R.string.heat_do_2, R.string.heat_dont_2),
                EmergencyStep(R.string.heat_step_3, R.string.heat_do_3, R.string.heat_dont_3)
            )
        ),
        EmergencyCase(
            id = 20, titleRes = R.string.emergency_unconscious, emoji = "😴",
            gradientStart = Color(0xFF7986CB), gradientEnd = Color(0xFF1A237E),
            steps = listOf(
                EmergencyStep(R.string.unconscious_step_1, R.string.unconscious_do_1, R.string.unconscious_dont_1),
                EmergencyStep(R.string.unconscious_step_2, R.string.unconscious_do_2, R.string.unconscious_dont_2),
                EmergencyStep(R.string.unconscious_step_3, R.string.unconscious_do_3, R.string.unconscious_dont_3)
            )
        )
    )
}
