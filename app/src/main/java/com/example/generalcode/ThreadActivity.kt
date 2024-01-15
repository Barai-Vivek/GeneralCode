package com.example.generalcode

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class ThreadActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())
    private var runnable : Runnable ?= null
    private var count = 1
    private var numberPrintingThread: NumberPrintingThread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.thread_activity)
        println("Thread screen")

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Thread"


    }

    fun runThread(view: View){
        //Run thread
        numberPrintingThread = NumberPrintingThread()
        numberPrintingThread?.start()
        //runHandler()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Stop the thread
        numberPrintingThread?.interrupt()
        // Remove the callbacks when the activity is destroyed
        handler.removeCallbacks(runnable?: Runnable {  })
    }

    inner class NumberPrintingThread : Thread() {
        override fun run() {
            while (!isInterrupted) {
                // Print the current number
                println("Number: $count")

                // Increment the count
                count++

                try {
                    // Sleep for 5 seconds
                    sleep(5000)
                } catch (e: InterruptedException) {
                    // Handle interruption and exit the thread
                    break
                }
            }
        }
    }

    private fun runHandler(){
        runnable = object : Runnable {
            override fun run() {
                // Print the current number
                println("Number: $count")

                // Increment the count
                count++

                // Schedule the runnable to run again after 5 seconds
                handler.postDelayed(this, 5000)
            }
        }
        handler.post(runnable as Runnable)
    }
}