package com.example.generalcode.services

import android.app.IntentService
import android.content.Intent
import android.util.Log

class IntentService: IntentService("IntentServiceExample") {
    @Deprecated("Deprecated in Java")
    override fun onHandleIntent(intent: Intent?) {
        // Perform background task here
        Log.d("IntentServiceExample", "IntentService executing...")
        val data = intent?.getStringExtra("data")
        Log.d("IntentServiceExample", "Data received: $data")
    }
}