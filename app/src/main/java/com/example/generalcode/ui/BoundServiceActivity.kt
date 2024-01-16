package com.example.generalcode.ui

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.generalcode.R
import com.example.generalcode.services.BoundService

class BoundServiceActivity : AppCompatActivity() {

    private lateinit var boundService: BoundService
    private var isBound = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as BoundService.LocalBinder
            boundService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bound_service)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Bound Service"
    }

    fun startService(view: View) {
        // Bind to the BoundService
        val boundServiceIntent = Intent(this, BoundService::class.java)
        bindService(boundServiceIntent, connection, BIND_AUTO_CREATE)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()

        // Unbind from the service
        if (isBound) {
            unbindService(connection)
            isBound = false
        }
    }
}