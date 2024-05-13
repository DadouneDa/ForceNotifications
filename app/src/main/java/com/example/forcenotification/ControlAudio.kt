package com.example.forcenotification

import android.content.Context
import android.media.AudioManager
import java.lang.Exception

object ControlAudio {
    fun disableSilentMode(context: Context) {
        try {
            val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
            audioManager.ringerMode = AudioManager.RINGER_MODE_NORMAL
            FileLogger.logToFile(context, "the func ${object{}.javaClass.enclosingMethod.name} ran successfully")
        } catch (e: Exception) {
            FileLogger.logToFile(context, "An error occurred: ${e.message}")

        }
    }
    fun setMaxVolume(context: Context) {
        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        try {
            // Increase Ring Volume to maximum
            audioManager.setStreamVolume(
                AudioManager.STREAM_RING,
                audioManager.getStreamMaxVolume(AudioManager.STREAM_RING),
                0
            )
            FileLogger.logToFile(context, "the func ${object{}.javaClass.enclosingMethod.name} ran successfully")
        } catch (e: Exception) {
            FileLogger.logToFile(context, "An error occurred: ${e.message}")
        }

        try {
            // Increase Notification Volume to maximum
            audioManager.setStreamVolume(
                AudioManager.STREAM_NOTIFICATION,
                audioManager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION),
                0
            )
            FileLogger.logToFile(context, "the func ${object{}.javaClass.enclosingMethod.name} ran successfully")
        } catch (e: Exception) {
            FileLogger.logToFile(context, "An error occurred: ${e.message}")
        }

        try {
            // Increase Notification Volume to maximum
            audioManager.setStreamVolume(
                AudioManager.STREAM_MUSIC,
                audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC),
                0
            )
            FileLogger.logToFile(context, "the func ${object{}.javaClass.enclosingMethod.name} ran successfully")
        } catch (e: Exception) {
            FileLogger.logToFile(context, "An error occurred: ${e.message}")
        }

        try {
            // Increase Notification Volume to maximum
            audioManager.setStreamVolume(
                AudioManager.STREAM_SYSTEM,
                audioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM),
                0
            )
            FileLogger.logToFile(context, "the func ${object{}.javaClass.enclosingMethod.name} ran successfully")
        } catch (e: Exception) {
            FileLogger.logToFile(context, "An error occurred: ${e.message}")
        }
        try {
            // Increase Notification Volume to maximum
            audioManager.setStreamVolume(
                AudioManager.STREAM_ALARM,
                audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM),
                0
            )
            FileLogger.logToFile(context, "the func ${object{}.javaClass.enclosingMethod.name} ran successfully")
        } catch (e: Exception) {
            FileLogger.logToFile(context, "An error occurred: ${e.message}")
        }
    }
}