import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.geoquizgo.R
import com.example.geoquizgo.ui.utils.playSound
import com.example.geoquizgo.ui.utils.switchLanguage


@Composable
fun LanguageToggle(activity: ComponentActivity) {
    val context = LocalContext.current
    val currentLocale = activity.resources.configuration.locales[0]
    val currentLang = currentLocale.language.lowercase()

    val flagRes = if (currentLang == "en") R.drawable.flag_en else R.drawable.flag_it

    IconButton(
        onClick = {
            playSound(context, R.raw.language)
            val newLang = if (currentLang == "en") "it" else "en"
            switchLanguage(activity, newLang)
        }
    ) {
        Image(
            painter = painterResource(id = flagRes),
            contentDescription = "Language Flag",
            modifier = Modifier
                .size(40.dp)
                .aspectRatio(1.6f) // or 1.5f
                .clip(RoundedCornerShape(0.dp)) // optional subtle rounding
        )
    }
}