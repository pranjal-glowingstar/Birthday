package com.apps.birthday.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.apps.birthday.core.common.AppConstants
import com.apps.birthday.presentation.navigation.NavigationComponent
import com.apps.birthday.presentation.viewmodel.MainViewModel
import com.apps.birthday.ui.theme.BirthdayTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
                NavigationComponent(viewModel)
            }
        }
        addCollectors()
    }
    private fun addCollectors(){
        lifecycleScope.launch {
            launch {
                viewModel.triggerEvent.collect { value ->
                    Log.d(AppConstants.DEBUG_TAG, value)
                }
            }
        }
    }
}