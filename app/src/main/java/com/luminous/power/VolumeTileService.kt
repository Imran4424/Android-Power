package com.luminous.power

import android.content.Context
import android.media.AudioManager
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService

class VolumeTileService : TileService() {
    override fun onClick() {
        super.onClick()
        val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        // This call nudges the system to display the volume UI without changing the volume level.
        audioManager.adjustVolume(AudioManager.ADJUST_SAME, AudioManager.FLAG_SHOW_UI)
    }

    override fun onStartListening() {
        super.onStartListening()
        qsTile?.apply {
            state = Tile.STATE_ACTIVE
            updateTile()
        }
    }
}
