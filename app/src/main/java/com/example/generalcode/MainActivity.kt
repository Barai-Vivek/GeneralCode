package com.example.generalcode

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

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
}