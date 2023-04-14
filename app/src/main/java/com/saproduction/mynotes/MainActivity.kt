package com.saproduction.mynotes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.saproduction.mynotes.Database.DatabaseObj
import com.saproduction.mynotes.Fragments.NotesFragment
import com.saproduction.mynotes.Fragments.SettingFragment
import com.saproduction.mynotes.Fragments.TodoFragment

class MainActivity : AppCompatActivity() {

    private lateinit var frameLayout: FrameLayout
    private lateinit var bottomNavigationView: BottomNavigationView

    var countBackedPressed = 0

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        frameLayout = findViewById(R.id.fragment_container)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        // Default fragment
        showFragment(NotesFragment())

        // Show fragment as per the selected bottom navigation item
        bottomNavigationView.setOnItemSelectedListener {

            Log.d("select", "true")

            val fragment = when (it.itemId) {
                R.id.notes -> NotesFragment()
                R.id.todo -> TodoFragment()
                R.id.setting -> SettingFragment()
                else -> NotesFragment()
            }

            showFragment(fragment)
            true
        }


    }

    override fun onBackPressed() {

        if(countBackedPressed == 0) {
            countBackedPressed++;
            Toast.makeText(this, "Press again to exit.", Toast.LENGTH_SHORT).show()
        }else {
            countBackedPressed = 0
            super.onBackPressed()
        }

    }

    fun showFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransition = fragmentManager.beginTransaction()

        fragmentTransition.replace(R.id.fragment_container, fragment)

        fragmentTransition.commit()
    }

    fun addNewNote(view: View) {
        val intent = Intent(this, NotesActivity::class.java)
        intent.putExtra("btnText", "Save")
        startActivity(intent)
        finish()
    }

}