package com.example.geoquizgo.ui


import com.example.geoquizgo.ui.utils.playSound
import androidx.compose.animation.core.EaseOutBack
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.geoquizgo.R
import com.example.geoquizgo.ui.utils.playSound
import kotlinx.coroutines.delay
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource


@Composable
fun FinalScoreScreen(
    score: Int,
    total: Int,
    onRestart: () -> Unit,
    onBack: () -> Unit
) {
    val context = LocalContext.current

    // This will run once when the screen is shown
    LaunchedEffect(Unit) {
        playSound(context, R.raw.complete)
    }

    // 5 star logic
    val percentage = score.toFloat() / total
    val starsEarned = when {
        percentage >= 1.0f -> 5
        percentage >= 0.8f -> 4
        percentage >= 0.6f -> 3
        percentage >= 0.4f -> 2
        percentage >= 0.2f -> 1
        else -> 0
    }

    val visibleStates = remember { List(5) { mutableStateOf(false) } }

    // Animate the stars after showing the screen
    LaunchedEffect(Unit) {
        visibleStates.forEachIndexed { index, state ->
            delay(index * 300L)
            state.value = true
        }
    }

    val composition by rememberLottieComposition(
        spec = when {
            starsEarned >= 4 -> LottieCompositionSpec.RawRes(R.raw.welldone)
            starsEarned in 2..3 -> LottieCompositionSpec.RawRes(R.raw.ok)
            else -> LottieCompositionSpec.RawRes(R.raw.good)
        }
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(id = R.string.quiz_finished),
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Stars Row with animation
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            repeat(5) { index ->
                val visible = visibleStates[index]

                val scale by animateFloatAsState(
                    targetValue = if (visible.value) 1f else 0f,
                    animationSpec = tween(durationMillis = 600, easing = EaseOutBack)
                )

                val alpha by animateFloatAsState(
                    targetValue = if (visible.value) 1f else 0f,
                    animationSpec = tween(durationMillis = 400)
                )

                Icon(
                    painter = painterResource(
                        id = if (index < starsEarned) R.drawable.ic_star_filled else R.drawable.ic_star_outline
                    ),
                    contentDescription = "Star $index",
                    tint = Color.Yellow,
                    modifier = Modifier
                        .size(48.dp)
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                            this.alpha = alpha
                        }
                        .padding(4.dp)
                )
            }
        }

        Text(
            text = stringResource(id = R.string.your_score, score, total),
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            modifier = Modifier.padding(bottom = 40.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Button(
                onClick = {
                    playSound(context, R.raw.click2)
                    onRestart()
                },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3E5BA9)),
                modifier = Modifier
                    .height(48.dp)
                    .weight(1f),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.restart_quiz),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            OutlinedButton(
                onClick = {
                    playSound(context, R.raw.click2)
                    onBack()
                },
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(2.dp, Color.White),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.White,
                    containerColor = Color(0x1AFFFFFF)
                ),
                modifier = Modifier
                    .height(48.dp)
                    .weight(1f),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.back_to_menu),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

