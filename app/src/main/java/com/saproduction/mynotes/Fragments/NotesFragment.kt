package com.saproduction.mynotes.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saproduction.mynotes.Database.DatabaseObj
import com.saproduction.mynotes.MainActivity
import com.saproduction.mynotes.Adapters.NoteAdapter
import com.saproduction.mynotes.R

class NotesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    lateinit var activity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_notes, container, false)

        // code to be written
        recyclerView = view.findViewById(R.id.recycler_view_notes)
        progressBar = view.findViewById(R.id.progressBar_notes)

        progressBar.visibility = View.VISIBLE
        activity = container!!.context as MainActivity

        loadDataInRv(activity)

        return view
    }

    private fun loadDataInRv(activity: MainActivity) {

        // thread to retrieve data from Room Database
        Thread {

            val db = DatabaseObj.getNotesDatabase(activity).notesDao()
            val noteList = db.getAll() // all the list of notes are present here!

            Log.d("list", "$db")

            // Thread to update UI
            activity.runOnUiThread {

                // show notes in list

                val adapter =
                    NoteAdapter(noteList, R.layout.notes_design_activity, activity)

                progressBar.visibility = View.INVISIBLE

                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(activity)
            }

        }.start()


    }


}