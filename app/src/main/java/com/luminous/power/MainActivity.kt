package com.luminous.power

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (PowerMenuAccessibilityService.instance != null) {
            PowerMenuAccessibilityService.instance?.showPowerMenu()
        } else {
            Toast.makeText(this, "Please enable Accessibility Service", Toast.LENGTH_SHORT).show()
            //startActivity(Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS))
        }

        finish()
    }
}