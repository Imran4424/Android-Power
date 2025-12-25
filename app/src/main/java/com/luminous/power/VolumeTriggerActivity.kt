package com.luminous.power

import android.app.Activity
import android.content.Context
import android.media.AudioManager
import android.os.Build
import android.os.Bundle

class VolumeTriggerActivity: Activity() {
        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)

                // Apply no-animation transition for modern Android
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                        overrideActivityTransition(Activity.OVERRIDE_TRANSITION_OPEN, 0, 0)
                } else {
                        @Suppress("DEPRECATION")
                        overridePendingTransition(0, 0)
                }

                // Trigger the native volume slider
                val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
                audioManager.adjustVolume(AudioManager.ADJUST_SAME, AudioManager.FLAG_SHOW_UI)

                // Close immediately
                finish()

                // Also apply no-animation for the closing transition
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                        overrideActivityTransition(Activity.OVERRIDE_TRANSITION_CLOSE, 0, 0)
                } else {
                        @Suppress("DEPRECATION")
                        overridePendingTransition(0, 0)
                }
        }
}