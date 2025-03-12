package com.luminous.power

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.luminous.power.ui.theme.PowerTheme

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