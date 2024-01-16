package com.example.generalcode.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.generalcode.R
import com.example.generalcode.services.IntentService

class IntentServiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_service)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Intent Service"
    }

    fun startService(view: View){
        // Start the IntentService
        val serviceIntent = Intent(this, IntentService::class.java)
        serviceIntent.putExtra("data", "Example Data")
        startService(serviceIntent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}