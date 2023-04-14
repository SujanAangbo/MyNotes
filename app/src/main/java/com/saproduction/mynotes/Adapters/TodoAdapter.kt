package com.saproduction.mynotes.Adapters

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.saproduction.mynotes.Database.DatabaseObj
import com.saproduction.mynotes.Database.TodoDatabase.TodoModel
import com.saproduction.mynotes.MainActivity
import com.saproduction.mynotes.R

class TodoAdapter(
    val context: Context,
    private val todoList: MutableList<TodoModel>,
    @LayoutRes val layout: Int
) : RecyclerView.Adapter<TodoAdapter.viewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(LayoutInflater.from(context).inflate(layout, parent, false))
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        // retrieve all the value in variables to use it easily
        val title = todoList[position].title
        val date = todoList[position].date
        val isChecked = todoList[position].isChecked
        val id = todoList[position].uid

        // set data in text and checkbox using upper data
        holder.titleTv.text = title
        holder.dateTv.text = date
        holder.checkBox.isChecked = isChecked

        // When item is clicked
        holder.itemView.setOnClickListener {

            // create a custom dialog
            val dialog = Dialog(context)

            dialog.setContentView(R.layout.alert_dialog_layout) // set layout
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ) // set width and height
            dialog.findViewById<EditText>(R.id.et_title).setText(title)
            dialog.findViewById<Button>(R.id.btn_add).setOnClickListener {

                val newTitle = dialog.findViewById<EditText>(R.id.et_title).text.toString()
                val updateTodo =
                    TodoModel(newTitle, id, isChecked, date) // created new todo to update

                val db = DatabaseObj.getTodoDatabase(context)
                Thread {
                    db.todoDao().updateTodo(updateTodo)
                    todoList[position].title =
                        newTitle // change the data of the todoList to show changes in the list
                    (context as MainActivity).runOnUiThread {
                        notifyDataSetChanged()
//                        Toast.makeText(context, "Todo Updated", Toast.LENGTH_SHORT).show()
                    }
                }.start()
                dialog.cancel()
            }

            dialog.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
                dialog.cancel()
            }
            dialog.show()
        }

        // delete when pressed the todo for long time
        holder.itemView.setOnLongClickListener {

            val alertDialog =
                AlertDialog.Builder(context)
                    .setTitle("Delete")
                    .setMessage("Do you want to delete this todo?")
                    .setPositiveButton("Delete") { dialog, it ->
                        Thread {
                            // delete with id
                            DatabaseObj.getTodoDatabase(context).todoDao().deleteTodo(id)
                            (context as MainActivity).runOnUiThread {

                                todoList.removeAt(position)
                                notifyDataSetChanged()
                                dialog.cancel()
                            }
                        }.start()

                    }
                    .setNegativeButton("No") { dialog, it ->
                        dialog.cancel()
                    }.show()

            true
        }

        // When checkbox is clicked
        holder.checkBox.setOnClickListener {
            // Do something when the checked state changes
//            val checked = holder.checkBox.isChecked
//            holder.checkBox.isChecked = !checked

            Log.d("date", isChecked.toString())

            val newTodo = TodoModel(title, id, !isChecked, date)

            val db = DatabaseObj.getTodoDatabase(context)

            Thread {
                db.todoDao().updateTodo(newTodo)
                (context as MainActivity).runOnUiThread {
                    todoList[position].isChecked = !isChecked
                    notifyDataSetChanged()
//                    Toast.makeText(context, "$position", Toast.LENGTH_SHORT).show()
                }
            }.start()
        }


    }

    override fun getItemCount(): Int {
        return todoList.size
    }


    class viewHolder(itemView: View) : ViewHolder(itemView) {
        var titleTv: TextView = itemView.findViewById(R.id.tv_title)
        var dateTv: TextView = itemView.findViewById(R.id.tv_date)
        var checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
    }
}