package com.saproduction.mynotes.Fragments

import android.content.Context
import android.content.Intent
import android.content.pm.InstrumentationInfo
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.saproduction.mynotes.Database.DatabaseObj
import com.saproduction.mynotes.MainActivity
import com.saproduction.mynotes.NotesActivity
import com.saproduction.mynotes.R

class SettingFragment : Fragment() {

    // this is used to change theme of the app. But it is not implemented yet
//    lateinit var isDark: Switch

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_setting, container, false)

        // code to be written

        val context = (container!!.context) as Context

//        isDark = view.findViewById(R.id.dark_mode_switch)


        return view
    }


}