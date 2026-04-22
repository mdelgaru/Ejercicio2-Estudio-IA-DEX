package com.example.ejercicio2_estudio_ia_dex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ejercicio2_estudio_ia_dex.presentation.FormScreen
import com.example.ejercicio2_estudio_ia_dex.presentation.MyFormsScreen
import kotlinx.serialization.Serializable

@Serializable
object MyForms
@Serializable
object NewForm

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = MyForms) {
                composable<MyForms> {
                    MyFormsScreen(
                        onNavigateToForm = {
                            navController.navigate(route = NewForm)
                        }
                    )
                }
                composable<NewForm> {
                    FormScreen(
                        onGoBack = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}