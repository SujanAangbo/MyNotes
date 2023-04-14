package com.saproduction.mynotes.Fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saproduction.mynotes.Database.DatabaseObj
import com.saproduction.mynotes.Database.TodoDatabase.TodoModel
import com.saproduction.mynotes.MainActivity
import com.saproduction.mynotes.R
import com.saproduction.mynotes.Adapters.TodoAdapter
import java.text.SimpleDateFormat
import java.util.*


class TodoFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var addNewTodo: ImageView
    lateinit var activity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_todo, container, false)

        // code to be written
        recyclerView = view.findViewById(R.id.recycler_view_todo)
        progressBar = view.findViewById(R.id.progressBar_todo)
        addNewTodo = view.findViewById(R.id.new_todo)

        activity = container!!.context as MainActivity

        // to add new todo to the list
        addNewTodo.setOnClickListener {

            var layout = R.layout.alert_dialog_layout

            //Dialog box
            val dialog = Dialog(activity)
            dialog.setContentView(layout)
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog.findViewById<Button>(R.id.btn_add).setOnClickListener {
                //code to insert todo into database
                val input = dialog.findViewById<EditText>(R.id.et_title).text.toString()

                val date = Calendar.getInstance().time
                val dateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm").format(date)

                // created new todo to insert into the list
                val newTodo = TodoModel(input, 0, false, dateFormat)

                val db = DatabaseObj.getTodoDatabase(activity)
                Thread {
                    db.todoDao().insertTodo(newTodo) // add todo to database

                    var list = db.todoDao().getAll() // add todo to list
                    activity.runOnUiThread {
                        showListWithRv(list) // update list
                    }
                }.start()

//                Toast.makeText(activity, "Item Inserted", Toast.LENGTH_SHORT).show()
                dialog.cancel()
            }
            dialog.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
                dialog.cancel()
            }

            dialog.show()

        }


        showTodoInRv(activity)

        return view
    }

    private fun showTodoInRv(activity: MainActivity) {

        val db = DatabaseObj.getTodoDatabase(activity)

        Thread {
            var todoList = db.todoDao().getAll()

            activity.runOnUiThread {
                showListWithRv(todoList)
            }

        }.start()

    }

    private fun showListWithRv(todoList: MutableList<TodoModel>) {
        progressBar.visibility = View.INVISIBLE
        recyclerView.adapter =
            TodoAdapter(activity, todoList, R.layout.todo_design_activity)
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

}

