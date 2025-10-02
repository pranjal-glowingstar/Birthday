package com.apps.birthday.core.receiver

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.apps.birthday.R
import com.apps.birthday.core.common.AppConstants
import com.apps.birthday.core.common.DispatcherProvider
import com.apps.birthday.data.dao.BirthdayDao
import com.apps.birthday.data.entity.BirthdayEntity
import com.apps.birthday.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@AndroidEntryPoint
class DateChangeReceiver : BroadcastReceiver() {

    @Inject
    lateinit var birthdayDao: BirthdayDao

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let { cntx ->
            if (intent?.action == Intent.ACTION_DATE_CHANGED) {
                CoroutineScope(DispatcherProvider.getIoDispatcher()).launch {
                    val currentDate = LocalDate.now().dayOfMonth
                    val currentMonth = LocalDate.now().monthValue
                    val birthdayList =
                        birthdayDao.getAllBirthdaysForGivenDate(currentDate, currentMonth)
                    if (birthdayList.isNotEmpty()) {
                        triggerLocalNotification(cntx, birthdayList)
                    }
                }
            }
        }
    }

    private fun triggerLocalNotification(context: Context, birthdayList: List<BirthdayEntity>) {
        createNotificationChannel(context)
        birthdayList.forEachIndexed { index, element ->
            val notification = buildNotification(context).apply {
                setContentTitle("Today is ${element.name}'s birthday!")
                setContentText("Congratulate ${element.name} for completing ${LocalDate.now().year - element.year} years and wish for more!")
            }
            with(NotificationManagerCompat.from(context)) {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return@with
                }
                notify(index, notification.build())
            }
        }
    }

    private fun createNotificationChannel(context: Context) {
        val name = AppConstants.CHANNEL_NAME
        val descriptionText = AppConstants.CHANNEL_DESCRIPTION
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel =
            NotificationChannel(AppConstants.NOTIFICATION_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun buildNotification(context: Context): NotificationCompat.Builder {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        return NotificationCompat.Builder(context, AppConstants.NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
    }
}