// ui/QuizScreen.kt
package com.example.geoquizgo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.graphics.Color
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.geoquizgo.R
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.ui.draw.clip
import com.example.geoquizgo.data.QuizQuestions
import com.example.geoquizgo.data.QuizState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.geoquizgo.ui.utils.playSound
import com.example.geoquizgo.ui.components.AnimatedProgressBar
import androidx.compose.ui.res.stringResource

@Composable
fun QuizScreen(onBackToMenu: () -> Unit) {
    val context = LocalContext.current
    val questions = QuizQuestions.getQuestions(
        QuizState.selectedCategory.value,
        QuizState.selectedLanguage.value
    )

    var currentIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var showAnswerResult by remember { mutableStateOf(false) }
    var selectedAnswerIndex by remember { mutableStateOf<Int?>(null) }
    var quizFinished by remember { mutableStateOf(false) }

    if (quizFinished) {
        FinalScoreScreen(
            score = score,
            total = questions.size,
            onRestart = {
                currentIndex = 0
                score = 0
                showAnswerResult = false
                selectedAnswerIndex = null
                quizFinished = false
            },
            onBack = onBackToMenu
        )
        return
    }

    val question = questions[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedProgressBar(current = currentIndex, total = questions.size)

        Text(
            text = "${currentIndex + 1} / ${questions.size}",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            ),
            color = Color.White,
            modifier = Modifier.padding(top = 5.dp)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = question.text,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        question.options.forEachIndexed { index, answer ->
            val isSelected = selectedAnswerIndex == index
            val isCorrect = index == question.correctIndex

            val targetColor = when {
                showAnswerResult && isSelected && isCorrect -> Color(0xFF4CAF50) // ✅ green
                showAnswerResult && isSelected && !isCorrect -> Color(0xFFF44336) // ❌ red
                showAnswerResult && isCorrect -> Color(0xFF4CAF50) // show correct even if not selected
                else -> Color(0xFF3E5BA9) // default blue
            }

            val animatedColor by animateColorAsState(
                targetValue = targetColor,
                animationSpec = tween(durationMillis = 500) // smooth fade
            )

            Button(
                onClick = {
                    if (selectedAnswerIndex == null) {
                        selectedAnswerIndex = index
                        showAnswerResult = true

                        // Play correct or incorrect sound
                        if (isCorrect) {
                            score++
                            playSound(context, R.raw.correct)
                        } else {
                            playSound(context, R.raw.wrong)
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(vertical = 10.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = animatedColor),
                enabled = true
            ) {
                Text(text = answer, style = MaterialTheme.typography.titleLarge.copy(color = Color.White))
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        AnimatedVisibility(visible = showAnswerResult) {
            val isLastQuestion = currentIndex == questions.lastIndex
            Button(onClick = {
                    showAnswerResult = false
                    selectedAnswerIndex = null
                    if (!isLastQuestion) currentIndex++ else quizFinished = true
                    }) {
                Text(
                    text = stringResource(
                        id = if (!isLastQuestion) R.string.next else R.string.finish
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        val animatedScore by animateIntAsState(
            targetValue = score,
            animationSpec = tween(durationMillis = 500)
        )

        Box(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF3E5BA9))
                .padding(horizontal = 16.dp, vertical = 6.dp)
        ) {
            Text(
                text = "Score: $animatedScore",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 23.sp
                )
            )
        }
    }
}



