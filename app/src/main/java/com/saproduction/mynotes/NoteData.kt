package com.saproduction.mynotes

import com.saproduction.mynotes.Database.NotesDatabase.NoteModel

// just for experiment purpose
class NoteData {

    lateinit var list: List<NoteModel>

    fun createNotes(): List<NoteModel> {
        var note1 = NoteModel(1, "First Note", "This is my first notes. d;gfdkdfgglslafksdfjsdjfsalkjfsjksdfjlsajf;lsjkasdjfajsdfjasdfjaskjflsfskdsdjfasjfkdsljsjdsljldjf", "10/11/2015")
        var note2 = NoteModel(2, "Second Note", "This is my second notes.", "10/11/2015")
        var note3 = NoteModel(3, "Third Note", "This is my third notes.", "10/11/2015")
        var note4 = NoteModel(4, "Fourth Note", "This is my fourth notes.", "10/11/2015")
        var note5 = NoteModel(5, "Fifth Note", "This is my fifth notes.", "10/11/2015")
        var note6 = NoteModel(6, "Sixth Note", "This is my sixth notes.", "10/11/2015")
        var note7 = NoteModel(7, "Seventh Note", "This is my seventh notes.", "10/11/2015")
        var note8 = NoteModel(8, "Eighth Note", "This is my eighth notes.", "10/11/2015")

        list = listOf<NoteModel>(note1, note2, note3, note4, note5, note6, note7, note8)

        return list

    }

}