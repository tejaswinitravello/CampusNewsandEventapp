package app.campusnewsevents.tejaswinis3567095


import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppNavGraph()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyAppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.Splash.route
    ) {
        composable(AppScreens.Splash.route) {
            LoadingScreenCheck(navController = navController)
        }

        composable(AppScreens.Login.route) {
            LoginScreen(navController = navController)
        }

        composable(AppScreens.Register.route) {
            RegistrationScreen(navController = navController)
        }









    }

}

@Composable
fun LoadingScreenCheck(navController: NavController) {

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        delay(3000)


            navController.navigate(AppScreens.Login.route) {
                popUpTo(AppScreens.Splash.route) {
                    inclusive = true
                }
            }


    }

    EntryScreen()
}



@Composable
fun EntryScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.bg_main)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.weight(1f))


            Image(
                modifier = Modifier.align(Alignment.CenterHorizontally)
                    .size(380.dp, 380.dp)
                    .padding(16.dp),
                painter = painterResource(id = R.drawable.app_icon),
                contentDescription = "News Sharing",
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Welcome To",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )

//            Spacer(modifier = Modifier.height(32.dp))
//            Spacer(modifier = Modifier.height(32.dp))

            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = "Campus News App\nby Tejaswini",
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))


        }
    }

}

@Preview(showBackground = true)
@Composable
fun EntryScreenMAPreview() {
    EntryScreen()
}