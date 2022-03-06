package com.odhiambodevelopers.paperdbtest

import android.app.Application
import io.paperdb.Paper


class NoteApp:Application() {
    override fun onCreate() {
        super.onCreate()
        Paper.init(applicationContext)
    }
}