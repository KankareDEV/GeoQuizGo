import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.geoquizgo.R
import com.example.geoquizgo.data.Category
import androidx.compose.ui.platform.LocalContext

import com.example.geoquizgo.data.QuizState
import com.example.geoquizgo.ui.utils.playSound

@Composable
fun CategoryScreen(
    activity: ComponentActivity,
    onCategorySelected: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 🔹 Language Toggle Row
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            LanguageToggle(activity)

        }
        // 🔹 Logo on Top
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .height(140.dp)
                .aspectRatio(1.6f)
                .padding(bottom = 32.dp)
        )

        // 🔹 Title
        Text(
            text = stringResource(R.string.choose_category),
            style = MaterialTheme.typography.headlineMedium.copy(color = Color.White),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // 🔹 Buttons
        CategoryButton(
            label = stringResource(R.string.europe),
            flagRes = R.drawable.flag_eu,
            onClick = {
                playSound(context, R.raw.click)
                QuizState.selectedCategory.value = Category.Europe
                onCategorySelected()
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        CategoryButton(
            label = stringResource(R.string.usa),
            flagRes = R.drawable.flag_usa,
            onClick = {
                playSound(context, R.raw.click)
                QuizState.selectedCategory.value = Category.USA
                onCategorySelected()
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        CategoryButton(
            label = stringResource(R.string.south_america),
            flagRes = R.drawable.flag_brazil,
            onClick = {
                playSound(context, R.raw.click)
                QuizState.selectedCategory.value = Category.SouthAmerica
                onCategorySelected()
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        CategoryButton(
            label = stringResource(R.string.asia),
            flagRes = R.drawable.flag_china,
            onClick = {
                playSound(context, R.raw.click)
                QuizState.selectedCategory.value = Category.Asia
                onCategorySelected()
            }
        )
    }
}
