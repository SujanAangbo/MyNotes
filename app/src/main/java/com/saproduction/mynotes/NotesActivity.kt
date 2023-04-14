package com.saproduction.mynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.saproduction.mynotes.Database.DatabaseObj
import com.saproduction.mynotes.Database.NotesDatabase.NoteModel
import java.text.SimpleDateFormat
import java.util.*

class NotesActivity : AppCompatActivity() {

    // variables for views
    private lateinit var titleTv: EditText
    private lateinit var descriptionTv: EditText
    private lateinit var saveOrUpdateBtn: Button
    private lateinit var dateTv: TextView

    // variables for storing intent data
    private var title: String? = null
    private var description: String? = null
    private var date: String? = null
    private var btnText: String? = null

    private var noteId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        initializeVariables()

        saveOrUpdateBtn.text = btnText
        showNote(title, description)

    }

    private fun initializeVariables() {
        titleTv = findViewById(R.id.title_tv)
        descriptionTv = findViewById(R.id.description_tv)
        saveOrUpdateBtn = findViewById(R.id.btn_save_update)
        dateTv = findViewById(R.id.tv_date)

        // get all the data from intent
        title = intent.getStringExtra("title")
        description = intent.getStringExtra("description")
        date = intent.getStringExtra("date")
        btnText = intent.getStringExtra("btnText")

        noteId = intent.getIntExtra("noteId", 0)
    }

    private fun showNote(title: String?, description: String?) {

        if (title != null) {
            titleTv.setText(title)
        }else {
            titleTv.setText("")
        }

        if(description != null) {
            descriptionTv.setText(description)
        }else {
            descriptionTv.setText("")
        }

        dateTv.text = date

    }

    override fun onBackPressed() {

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }

    fun saveNotes(view: View) {

        // get all the updated title, description and date from the edit text
        val notesTitle = titleTv.text.toString()
        val notesDescription = descriptionTv.text.toString()
        val sdf = SimpleDateFormat("d/M/yyy hh:mm")

        val date = sdf.format(Date())

        Log.d("time", date)

        // create database instance
        val db = DatabaseObj.getNotesDatabase(applicationContext)

        val note = NoteModel(noteId, notesTitle, notesDescription, date)

        Log.d("check", "$noteId")
        Log.d("check", saveOrUpdateBtn.text.toString())

        Thread {
            if (saveOrUpdateBtn.text.toString() == "Update") {
                db.notesDao().updateNote(note)
            } else {
                db.notesDao().insert(note)
            }
        }.start()

        startActivity(Intent(this, MainActivity::class.java))
        finishAffinity()

    }
}