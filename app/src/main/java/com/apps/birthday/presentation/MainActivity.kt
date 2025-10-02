package com.apps.birthday.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
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
        if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)
        }
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