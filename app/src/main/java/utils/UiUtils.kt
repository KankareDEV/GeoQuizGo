package com.example.geoquizgo.ui.utils

import android.content.Context
import android.media.MediaPlayer
import androidx.activity.ComponentActivity
import com.example.geoquizgo.data.Language
import com.example.geoquizgo.data.QuizState
import java.util.Locale

fun switchLanguage(activity: ComponentActivity, langCode: String) {
    val locale = Locale(langCode)
    Locale.setDefault(locale)
    val config = activity.resources.configuration
    config.setLocale(locale)

    // Update QuizState
    QuizState.selectedLanguage.value = if (langCode == "it") Language.IT else Language.EN

    activity.resources.updateConfiguration(config, activity.resources.displayMetrics)
    activity.recreate()
}

fun playSound(context: Context, resId: Int) {
    val mediaPlayer = MediaPlayer.create(context, resId)
    mediaPlayer.start()
    mediaPlayer.setOnCompletionListener { it.release() }
}