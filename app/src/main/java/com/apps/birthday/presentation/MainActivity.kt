package com.apps.birthday.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.apps.birthday.presentation.composable.BottomNavBar
import com.apps.birthday.presentation.navigation.Navigation
import com.apps.birthday.presentation.viewmodel.MainViewModel
import com.apps.birthday.ui.theme.BirthdayTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BirthdayTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.safeDrawingPadding(),
                    bottomBar = { BottomNavBar(navController) }) { _ ->
                    Navigation(navController)
                }
            }
        }
    }
}