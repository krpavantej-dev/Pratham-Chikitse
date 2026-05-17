package com.example.mindmatrix.audio

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.Locale

interface ITTSManager {
    fun speak(text: String)
    fun stop()
    fun shutdown()
}

class TTSManager(context: Context) : ITTSManager {
    private var tts: TextToSpeech? = null
    private var isInitialized = false

    init {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tts?.setLanguage(Locale("kn", "IN"))
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    tts?.setLanguage(Locale.US)
                }
                isInitialized = true
            }
        }
    }

    override fun speak(text: String) {
        if (isInitialized) {
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    override fun stop() {
        tts?.stop()
    }

    override fun shutdown() {
        tts?.shutdown()
    }
}

class DummyTTSManager : ITTSManager {
    override fun speak(text: String) {}
    override fun stop() {}
    override fun shutdown() {}
}
