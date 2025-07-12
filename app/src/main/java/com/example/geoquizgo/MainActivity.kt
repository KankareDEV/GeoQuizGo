package com.example.geoquizgo

import CategoryScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.geoquizgo.ui.QuizScreen
import com.example.geoquizgo.ui.theme.GeoQuizGoTheme
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

enum class AppScreen {
    Category, Quiz
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeoQuizGoTheme {
                val currentScreen = remember { mutableStateOf(AppScreen.Category) }

                val gradientBackground = Brush.verticalGradient(
                    colors = listOf(Color(0xFF1976D2), Color(0xFF64B5F6))
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = gradientBackground)
                        .padding(horizontal = 32.dp, vertical = 40.dp)
                ) {
                    val activity = this@MainActivity

                    @OptIn(ExperimentalAnimationApi::class)
                    AnimatedContent(
                        targetState = currentScreen.value,
                        transitionSpec = {
                            slideInHorizontally { width -> width } + fadeIn() togetherWith
                                    slideOutHorizontally { width -> -width } + fadeOut()
                        }
                    ) { screen ->
                        when (screen) {
                            AppScreen.Category -> CategoryScreen(
                                activity = activity,
                                onCategorySelected = { currentScreen.value = AppScreen.Quiz }
                            )
                            AppScreen.Quiz -> QuizScreen(
                                onBackToMenu = { currentScreen.value = AppScreen.Category }
                            )
                        }
                    }
                }
            }
        }
    }
}







