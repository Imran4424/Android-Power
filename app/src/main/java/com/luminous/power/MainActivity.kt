package com.luminous.power

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (PowerMenuAccessibilityService.instance != null) {
            PowerMenuAccessibilityService.instance?.showPowerMenu()
            finish()
        } else {
//            Toast.makeText(this, "Please enable Accessibility Service", Toast.LENGTH_SHORT).show()
//            startActivity(Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS))
            showTermsDialog()
        }
    }

    private fun showTermsDialog() {
        // Build the alert dialog
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Accessibility Permission")
        builder.setMessage("Please give us accessibility permission. We need this permission to show power menu.")
        builder.setCancelable(false) // Prevent dismissing by tapping outside or using back button

        builder.setPositiveButton("Accept") { dialog, _ ->
            dialog.dismiss()
            // Continue with the app as needed
            startActivity(Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS))
            finish()
        }

        builder.setNegativeButton("Decline") { dialog, _ ->
            // Optionally inform the user and then close the app
            dialog.dismiss()
            finish() // End the activity/app if terms are declined
        }

        // Create and show the dialog
        val alertDialog = builder.create()
        alertDialog.show()
    }
}