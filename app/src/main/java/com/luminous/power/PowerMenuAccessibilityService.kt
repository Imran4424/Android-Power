package com.luminous.power

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

class PowerMenuAccessibilityService: AccessibilityService() {
    companion object {
        var instance: PowerMenuAccessibilityService? = null
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        instance = this // Store the instance for global access
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

    override fun onDestroy() {
        super.onDestroy()
        instance = null
    }
}