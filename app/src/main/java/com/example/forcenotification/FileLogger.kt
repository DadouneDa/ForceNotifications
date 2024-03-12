package com.example.forcenotification

import android.content.Context
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*

object FileLogger {
    private const val FILENAME = "app_logs.txt"
    private const val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"

    fun logToFile(context: Context, message: String) {
        val timestamp = SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(Date())
        val logMessage = "$timestamp: $message\n"

        try {
            val file = File(context.getExternalFilesDir(null), FILENAME)
            val writer = FileWriter(file, true)
            writer.append(logMessage)
            writer.flush()
            writer.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
