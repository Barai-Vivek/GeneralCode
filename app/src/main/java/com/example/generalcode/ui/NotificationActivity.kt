package com.example.generalcode.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.generalcode.R

class NotificationActivity : AppCompatActivity() {

    companion object {
        private const val NOTIFICATION_CHANNEL_ID = "notification_channel_id"
        private const val NOTIFICATION_ID = 10
        private const val WORK_TAG = "notification_work_tag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notification_activity)

        println("Notification screen")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Notification"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }
    }

    fun pushNotification(view: View) {
        //code to show push notification
        // Create a WorkRequest
        val notificationWork = OneTimeWorkRequestBuilder<NotificationWorker>()
            .addTag(WORK_TAG)
            .build()

        // Enqueue the WorkRequest to WorkManager
        WorkManager.getInstance(this).enqueue(notificationWork)
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notification Channel"
            val descriptionText = "Description for Notification Channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            // Register the channel with the system
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    class NotificationWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
        override fun doWork(): Result {
            // Do the work in the background
            showNotification()

            // Indicate whether the work finished successfully or with an error
            return Result.success()
        }

        private fun showNotification() {
            val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // Create a notification
            val builder = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("WorkManager Notification")
                .setContentText("This is a notification from WorkManager.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(createPendingIntent())

            // Show the notification
            notificationManager.notify(NOTIFICATION_ID, builder.build())
        }

        private fun createPendingIntent(): PendingIntent {
            val resultIntent = Intent(applicationContext, MainActivity::class.java)
            val requestCode = System.currentTimeMillis().toInt()

            return PendingIntent.getActivity(
                applicationContext,
                requestCode,
                resultIntent,
                PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        }
    }
}