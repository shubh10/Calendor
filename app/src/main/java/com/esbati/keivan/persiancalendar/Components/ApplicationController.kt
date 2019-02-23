package com.esbati.keivan.persiancalendar.Components

import android.app.Application
import android.content.Context
import android.content.Intent
import com.crashlytics.android.Crashlytics
import com.esbati.keivan.persiancalendar.Features.Notification.ApplicationService
import com.esbati.keivan.persiancalendar.Features.Notification.NotificationHelper
import com.esbati.keivan.persiancalendar.Features.Notification.NotificationUpdateService
import com.esbati.keivan.persiancalendar.R
import com.esbati.keivan.persiancalendar.Utils.AndroidUtilities
import io.fabric.sdk.android.Fabric
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

/**
 * Created by Esbati on 12/22/2015.
 */
class ApplicationController : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this

        SoundManager.init()
        Fabric.with(this, Crashlytics())
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/IRANSans(FaNum).ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )

        //Show Sticky Notification
        NotificationHelper.createNotificationChannel(this)
        val notificationIntent = Intent(this, NotificationUpdateService::class.java)
        startService(notificationIntent)

        //Start Application Service if Not Running
        if (!AndroidUtilities.isServiceRunning(ApplicationService::class.java))
            startService(Intent(baseContext, ApplicationService::class.java))
    }

    companion object {
        private lateinit var appContext: Context

        @JvmStatic fun getContext() = appContext
    }
}
