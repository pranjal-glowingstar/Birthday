package com.apps.birthday.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.apps.birthday.core.common.AppConstants
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.net.toUri

@AndroidEntryPoint
class NotificationsActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        intent?.extras?.let {
            val contact = it.getString(AppConstants.USER_CONTACT)
            val message = it.getString(AppConstants.MESSAGE)

            contact?.let { cnt ->
                message?.let { msg ->
                    val url = "https://wa.me/$cnt?text=${Uri.encode(msg)}"
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = url.toUri()
                        setPackage("com.whatsapp")
                    }
                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                    } else {
                        makeToast()
                    }
                } ?: makeToast()
            } ?: makeToast()
        } ?: makeToast()
        finish()
    }
    private fun makeToast() {
        Toast.makeText(this, "Unable to send message. Please check contact and message", Toast.LENGTH_SHORT).show()
    }
}