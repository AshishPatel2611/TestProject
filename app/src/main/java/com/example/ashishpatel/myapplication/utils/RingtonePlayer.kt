package com.example.ashishpatel.myapplication.utils

import android.content.Context
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log

/**
 * QuickBlox team
 */
class RingtonePlayer {
    private var mediaPlayer: MediaPlayer? = null
    private var context: Context? = null

    private// notification is null, using backup
            // I can't see this ever being null (as always have a default notification)
            // but just incase
            // notification backup is null, using 2nd backup
    val notification: Uri?
        get() {
            var notification: Uri? = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)

            if (notification == null) {
                notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                if (notification == null) {
                    notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
                }
            }
            return notification
        }

    constructor(context: Context, resource: Int) {
        this.context = context
        mediaPlayer = MediaPlayer.create(context, resource)
    }

    constructor(context: Context) {
        this.context = context
        val notification = notification
        if (notification != null) {
            mediaPlayer = MediaPlayer.create(context, notification)
        }
    }

    fun play(looping: Boolean) {
        Log.i(TAG, "play")
        if (mediaPlayer == null) {
            Log.i(TAG, "mediaPlayer isn't created ")
            return
        }
        mediaPlayer!!.isLooping = looping
        mediaPlayer!!.start()
    }

    @Synchronized
    fun stop() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer!!.stop()
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            }

            mediaPlayer!!.release()
            mediaPlayer = null
        }
    }

    companion object {

        private val TAG = RingtonePlayer::class.java.simpleName
    }
}
