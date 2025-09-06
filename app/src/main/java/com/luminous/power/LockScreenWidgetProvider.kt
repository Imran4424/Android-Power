package com.luminous.power

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class LockScreenWidgetProvider : AppWidgetProvider() {
        override fun onUpdate(
                context: Context,
                appWidgetManager: AppWidgetManager,
                appWidgetIds: IntArray
        ) {
                for (id in appWidgetIds) {
                        val views = RemoteViews(context.packageName, R.layout.widget_lock)

                        // Broadcast only within this package
                        val intent = Intent(Actions.ACTION_LOCK).setPackage(context.packageName)
                        val pending = PendingIntent.getBroadcast(
                                context,
                                0,
                                intent,
                                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                        )

                        views.setOnClickPendingIntent(R.id.btnLock, pending)
                        appWidgetManager.updateAppWidget(id, views)
                }
        }

        companion object {
                fun requestUpdate(context: Context) {
                        val manager = AppWidgetManager.getInstance(context)
                        val cn = ComponentName(context, LockScreenWidgetProvider::class.java)
                        val ids = manager.getAppWidgetIds(cn)
                        if (ids.isNotEmpty()) {
                                (LockScreenWidgetProvider()).onUpdate(context, manager, ids)
                        }
                }
        }
}