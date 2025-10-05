package com.apps.birthday.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.apps.birthday.core.common.AppConstants
import com.apps.birthday.core.receiver.DateChangeReceiver
import com.apps.birthday.presentation.navigation.NavigationComponent
import com.apps.birthday.presentation.viewmodel.AddScreenViewModel
import com.apps.birthday.presentation.viewmodel.HomeScreenViewModel
import com.apps.birthday.presentation.viewmodel.MainViewModel
import com.apps.birthday.presentation.viewmodel.UpcomingScreenViewModel
import com.apps.birthday.ui.theme.BirthdayTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Calendar

@AndroidEntryPoint
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val homeScreenViewModel: HomeScreenViewModel by viewModels()
    private val addScreenViewModel: AddScreenViewModel by viewModels()
    private val upcomingScreenViewModel: UpcomingScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)
        }
        scheduleMidnightAlarm()
        setContent {
            BirthdayTheme {
                NavigationComponent(
                    viewModel,
                    homeScreenViewModel,
                    addScreenViewModel,
                    upcomingScreenViewModel
                )
            }
        }
        addCollectors()
    }

    private fun addCollectors() {
        lifecycleScope.launch {
            launch {
                viewModel.triggerEvent.collect { value ->
                    Log.d(AppConstants.DEBUG_TAG, value)
                }
            }
        }
    }

    private fun scheduleMidnightAlarm() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(this, DateChangeReceiver::class.java).apply {
            action = AppConstants.MIDNIGHT_ACTION
        }
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            add(Calendar.DAY_OF_MONTH, 1)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (alarmManager.canScheduleExactAlarms()) {
                alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
                )
            } else {
                val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                startActivity(intent)
            }
        } else {
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        }
    }

}