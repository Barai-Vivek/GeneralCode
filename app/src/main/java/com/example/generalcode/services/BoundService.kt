package com.example.generalcode.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import java.util.concurrent.Executors

class BoundService : Service() {

    private val binder = LocalBinder()
    private val taskExecute = Executors.newSingleThreadExecutor()
    private var handler = Handler(Looper.getMainLooper())

    override fun onBind(intent: Intent?): IBinder {
        Log.d("BoundService", "Service onBind")
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("BoundService", "Service onCreate")
    }

    inner class LocalBinder : Binder() {
        fun getService(): BoundService = this@BoundService
    }

    fun executeTask(task: () -> Unit) {
        Log.d("BoundService", "task executed")
        taskExecute.execute(task)
    }

    fun scheduleTask(task: () -> Unit, delayMillis: Long) {
        Log.d("BoundService", "task scheduled")
        handler.postDelayed(task, delayMillis)
    }

    override fun onDestroy() {
        super.onDestroy()
        taskExecute.shutdown()
        handler.removeCallbacksAndMessages(null)
        Log.d("BoundService", "Service onDestroy")
    }
}