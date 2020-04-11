package com.sun.warning

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Handler
import android.os.IBinder
import android.util.Log

class MyService : Service(){
    private var mediaPlayer: MediaPlayer? = null
    private var handler: Handler? = null
    private var rcode: Int = 0
    override fun onCreate() {
        super.onCreate()
        handler = Handler()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        handler?.post(WarningAsync(handler!!))

        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    inner class WarningAsync(private var handle: Handler): Runnable, OnFetch {

        override fun run() {
            Warning(this).execute("https://elearning.hueic.edu.vn")
            if (rcode != 200 && rcode != 0) {
                mediaPlayer = MediaPlayer.create(applicationContext, R.raw.warning)
                mediaPlayer?.start()
                handle.postDelayed(this, 2000)
            } else {
                handle.postDelayed(this, 10000)
            }
        }

        override fun getResponse(requestCode: Int) {
            rcode = requestCode
        }

    }
}
