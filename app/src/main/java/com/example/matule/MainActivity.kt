package com.example.matule

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.matule.domain.UseCases
import com.example.matule.presentation.CameraScreen
import com.example.matule.presentation.CameraScreen1
import com.example.matule.presentation.screens.CardScreen
import com.example.matule.presentation.screens.CreatePasswordScreen
import com.example.matule.presentation.screens.CreateProfileScreen
import com.example.matule.presentation.screens.CreateProjectScreen
import com.example.matule.presentation.screens.LoginScreen
import com.example.matule.presentation.screens.MainScreen
import com.example.matule.presentation.screens.PasswordScreen
import com.example.matule.presentation.screens.SplashScreen
import com.example.matule.presentation.screens.TelegramOTPScreen
import com.example.matule.presentation.ui.theme.MatuleTheme
import com.example.network.Network
import com.example.network.NetworkImpl
import com.example.ui_kit.White
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.gson.Gson
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

val LocalNetwork = staticCompositionLocalOf<Network> {
    error("Network not found")
}

val LocalUseCases = staticCompositionLocalOf<UseCases> {
    error("UseCaseN not found")
}

@Serializable
data class User(
    val id: Int,
    val name: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            LaunchedEffect(Unit) {
                val user = User(1,"dan")
                Log.e("UserSerializable", Json.encodeToString(user))
                Log.e("UserSerializable", Gson().toJson(user))
                Log.e("UserSerializable", jacksonObjectMapper().writeValueAsString(user))
            }

            val navController = rememberNavController()

            CompositionLocalProvider(
                LocalNetwork provides NetworkImpl(),
//                LocalUseCases provides UseCases(LocalNetwork.current)
            ) {
                MatuleTheme(
                    dynamicColor = false
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        containerColor = White
                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = "CameraScreen",
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable("CameraScreen") {
                                CameraScreen1()
                            }

                            composable("CardScreen") {
                                CardScreen()
                            }

                            composable("splash") {
                                SplashScreen(
                                    navController = navController
                                )
                            }

                            composable("LoginScreen") {
                                LoginScreen(
                                    navController = navController
                                )
                            }

                            composable("CreatePasswordScreen") {
                                CreatePasswordScreen(
                                    navController = navController
                                )
                            }

                            composable("CreateProfileScreen") {
                                CreateProfileScreen(
                                    navController = navController
                                )
                            }

                            composable("TelegramOTPScreen") {
                                TelegramOTPScreen(
                                    navController = navController
                                )
                            }

                            composable("PasswordScreen") {
                                PasswordScreen(
                                    navController = navController
                                )
                            }

                            composable("MainScreen") {
                                MainScreen(
                                    navController = navController
                                )
                            }

                            composable("CreateProjectScreen") {
                                CreateProjectScreen(
                                    navController = navController
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}