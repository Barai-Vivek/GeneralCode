package com.example.generalcode.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.generalcode.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun notification(view: View) {
        val notificationIntent  = Intent(applicationContext, NotificationActivity::class.java)
        startActivity(notificationIntent)
    }
    fun thread(view: View) {
        val threadIntent  = Intent(applicationContext, ThreadActivity::class.java)
        startActivity(threadIntent)
    }
    fun sharedPreferences(view: View) {
        val sharedPreferencesIntent  = Intent(applicationContext, EncryptedPreferencesActivity::class.java)
        startActivity(sharedPreferencesIntent)
    }

    fun foregroundService(view: View) {
        val foregroundServiceIntent  = Intent(applicationContext, ForegroundServiceActivity::class.java)
        startActivity(foregroundServiceIntent)
    }

    fun intentService(view: View) {
        val intentServiceIntent  = Intent(applicationContext, IntentServiceActivity::class.java)
        startActivity(intentServiceIntent)
    }

    fun boundService(view: View) {
        val boundServiceIntent  = Intent(applicationContext, BoundServiceActivity::class.java)
        startActivity(boundServiceIntent)
    }
}