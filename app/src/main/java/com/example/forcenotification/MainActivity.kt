package com.example.forcenotification
import com.example.forcenotification.FileLogger  // Replace with your actual package name
import com.example.forcenotification.ControlAudio  // Replace with your actual package name

import android.animation.ArgbEvaluator
import android.widget.EditText
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Context
import android.media.AudioManager
import java.lang.Exception


private const val TAG = "MainActivity"
private const val INITIAL_TIP_PERCENT = 10


class MainActivity : AppCompatActivity() {
    private lateinit var etBaseAmount :EditText
    private lateinit var seekBarTip :SeekBar
    private lateinit var tvTipPercentLabel :TextView
    private lateinit var tvTipAmount :TextView
    private lateinit var tvTotalAmount :TextView
    private lateinit var tvTipDescription :TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        etBaseAmount = findViewById(R.id.etBaseAmount)
        seekBarTip = findViewById(R.id.seekBarTip)
        tvTipPercentLabel = findViewById(R.id.tvTipPercentLabel)
        tvTipAmount = findViewById(R.id.tvTipAmount)
        tvTotalAmount = findViewById(R.id.tvTotalAmount)
        tvTipDescription = findViewById(R.id.tvTipDescription)

        seekBarTip.progress = INITIAL_TIP_PERCENT
        tvTipPercentLabel.text = "$INITIAL_TIP_PERCENT%"
        updateTipDescription(INITIAL_TIP_PERCENT)


        ControlAudio.disableSilentMode(this@MainActivity)
        ControlAudio.setMaxVolume(this@MainActivity)


        seekBarTip.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.i(TAG, "OnProgressChange $p1")
                tvTipPercentLabel.text = "$p1%"
                computeTipAndTotal()
                updateTipDescription(p1)
                ControlAudio.disableSilentMode(this@MainActivity)
//                disableSilentMode(this@MainActivity)
                ControlAudio.setMaxVolume(this@MainActivity)
//                setMaxVolume(this@MainActivity)

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        etBaseAmount.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.i(TAG, "AfterTextChanged $p0")
                computeTipAndTotal()
            }

        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
//    private fun setMaxVolume(context: Context) {
//        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
//        try {
//            // Increase Ring Volume to maximum
//            audioManager.setStreamVolume(
//                AudioManager.STREAM_RING,
//                audioManager.getStreamMaxVolume(AudioManager.STREAM_RING),
//                0
//            )
//        } catch (e: Exception) {
//            FileLogger.logToFile(context, "An error occurred: ${e.message}")
//        }
//
//        try {
//            // Increase Notification Volume to maximum
//            audioManager.setStreamVolume(
//                AudioManager.STREAM_NOTIFICATION,
//                audioManager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION),
//                0
//            )
//        } catch (e: Exception) {
//            FileLogger.logToFile(context, "An error occurred: ${e.message}")
//        }
//    }
//    private fun disableSilentMode(context: Context) {
//        try {
//            val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
//            audioManager.ringerMode = AudioManager.RINGER_MODE_NORMAL
//        } catch (e: Exception) {
//            FileLogger.logToFile(context, "An error occurred: ${e.message}")
//        }
//    }

    private fun updateTipDescription(tipPercent: Int) {
        val tipDecription = when (tipPercent){
            in 0..9 -> "Poor"
            in 10..14 -> "Acceptable"
            in 15..19 -> "Good"
            in 20..24 -> "Great"
            else -> "Amazing"
        }
        tvTipDescription.text = tipDecription

        val color = ArgbEvaluator().evaluate(
            tipPercent.toFloat() / seekBarTip.max,
            ContextCompat.getColor(this, R.color.color_worst_tip),
            ContextCompat.getColor(this, R.color.color_best_tip)
        ) as Int
        tvTipDescription.setTextColor(color)

    }

    private fun computeTipAndTotal() {
        // 1. get value of base tip
        val baseAmountText = etBaseAmount.text.toString()

        if (baseAmountText.isNotBlank()) {

            val baseAmount = etBaseAmount.text.toString().toDouble()
            val tipPercent = seekBarTip.progress
            // 2. compute the tip and total

            val tipAmaount = baseAmount * tipPercent / 100
            val totalAmount = baseAmount + tipAmaount
            //3. update the UI
            tvTipAmount.text = "%.2f".format(tipAmaount)
            tvTotalAmount.text = "%.2f".format(totalAmount)
        } else {
        }
    }

}
