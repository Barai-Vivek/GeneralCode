package com.example.generalcode.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.generalcode.R
import com.example.generalcode.services.ForegroundService

class ForegroundServiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foreground_service)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Foreground Service"
    }

    fun startService(view: View){
        // Start the ForegroundService
        val serviceIntent = Intent(this, ForegroundService::class.java)
        startService(serviceIntent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}