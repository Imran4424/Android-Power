package com.luminous.power

import android.accessibilityservice.AccessibilityService
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.view.accessibility.AccessibilityEvent

class PowerMenuAccessibilityService: AccessibilityService() {
    companion object {
        var instance: PowerMenuAccessibilityService? = null
    }

    // BroadcastReceiver to react to widget taps
    private val lockReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == Actions.ACTION_LOCK) {
                // Lock immediately
                performGlobalAction(GLOBAL_ACTION_LOCK_SCREEN)
            }
        }
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        instance = this // Store the instance for global access
        // Listen only while service is alive
        val filter = IntentFilter(Actions.ACTION_LOCK)
        if (Build.VERSION.SDK_INT >= 33) {
            registerReceiver(lockReceiver, filter, Context.RECEIVER_NOT_EXPORTED)
        } else {
            @Suppress("UnspecifiedRegisterReceiverFlag")
            registerReceiver(lockReceiver, filter)
        }
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // Not needed for this functionality
    }

    override fun onInterrupt() {
        // Not needed
    }

    fun showPowerMenu() {
        performGlobalAction(GLOBAL_ACTION_POWER_DIALOG)
    }

    private fun unregisterReceiverSafe() = runCatching { unregisterReceiver(lockReceiver) }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiverSafe()
        instance = null
    }
}