package com.saproduction.mynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.provider.ContactsContract.Intents
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import com.saproduction.mynotes.Database.DatabaseObj
import com.saproduction.mynotes.Database.SettingsDatabase.Settings
import com.saproduction.mynotes.Database.SettingsDatabase.SettingsDatabase

class SplashScreen : AppCompatActivity() {

    lateinit var db: SettingsDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        Thread {
            Thread.sleep(2000)

            db = DatabaseObj.getSettingsDatabase(this)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }.start()

    }

}