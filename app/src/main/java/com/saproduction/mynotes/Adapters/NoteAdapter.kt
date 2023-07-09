package com.saproduction.mynotes.Adapters

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.saproduction.mynotes.Database.DatabaseObj
import com.saproduction.mynotes.Database.NotesDatabase.NoteModel
import com.saproduction.mynotes.MainActivity
import com.saproduction.mynotes.NotesActivity
import com.saproduction.mynotes.R

class NoteAdapter(
    private var list: MutableList<NoteModel>,
    @LayoutRes var layout: Int,
    private var context: Context
) :
    Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(layout, parent, false)

        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        val title = list[position].title
        val description = list[position].description
        val date = list[position].date
        val id = list[position].id

        holder.titleTv.text = title
        holder.descriptionTv.text = description
        holder.dateTv.text = date

        // Show notes in the new page
        holder.itemView.setOnClickListener {

            val intent = Intent(context, NotesActivity::class.java)

            intent.putExtra("title", title)
            intent.putExtra("description", description)
            intent.putExtra("date", date)

            // this message is used for update operation in the next page and set button
            intent.putExtra("btnText", "Update")
            intent.putExtra("noteId", id)

            context.startActivity(intent)

        }

        // Delete data from database
        holder.deleteTv.setOnClickListener {

            // here

            AlertDialog.Builder(context)
                .setTitle("Delete")
                .setMessage("Do you want to delete this todo?")
                .setPositiveButton("Delete") { dialog, it ->
                    Thread {
                        // delete with id
                        val db = DatabaseObj.getNotesDatabase(context)
                        db.notesDao().deleteById(id) // remove data from the database

                        (context as MainActivity).runOnUiThread {

                            // first remove element from the list and then only call notifyDataSetChanged() method
                            list.removeAt(position) // remove data from the RV list
                            notifyDataSetChanged()
                            dialog.cancel()
                        }
                    }.start()

                }
                .setNegativeButton("No") { dialog, it ->
                    dialog.cancel()
                }.show()
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class NoteViewHolder(itemView: View) : ViewHolder(itemView) {
        var titleTv: TextView = itemView.findViewById(R.id.tv_title)
        var descriptionTv: TextView = itemView.findViewById(R.id.tv_description)
        var dateTv: TextView = itemView.findViewById(R.id.tv_date)
        var deleteTv: ImageView = itemView.findViewById(R.id.tv_delete)
    }
}