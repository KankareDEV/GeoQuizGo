package com.example.geoquizgo.data

import androidx.compose.runtime.mutableStateOf

object QuizState {
    var selectedLanguage = mutableStateOf(Language.EN)
    var selectedCategory = mutableStateOf(Category.Europe)
}