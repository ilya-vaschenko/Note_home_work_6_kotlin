package com.example.note_home_work_6

import android.content.res.Configuration
import com.example.note_home_work_6.NoteTextFragment.Companion.newInstance
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.note_home_work_6.R
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.note_home_work_6.NoteTextFragment
import com.example.note_home_work_6.NoteListFragment
import java.util.ArrayList

class NoteListFragment : Fragment() {
    private var notes: ArrayList<Note>? = null
    private var isLand = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setReenterTransition(true);
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_note_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isLand = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        initView(view)
    }

    private fun initView(view: View) {
        val linearLayout = view as LinearLayout
        createNote()
        for (i in notes!!.indices) {
            val textView = TextView(context)
            textView.text = notes!![i].name
            textView.textSize = 25f
            linearLayout.addView(textView)
            textView.setOnClickListener { v: View? -> showNote(notes!![i]) }
        }
    }

    private fun showNote(note: Note) {
        if (isLand) {
            showNoteLand(note)
        } else {
            showNotePort(note)
        }
    }

    private fun createNote() {
        notes = ArrayList()
        notes!!.add(Note("Список важных дел", "26.07.2021", "Поспать уже наконец так, чтоб выспаться!"))
        notes!!.add(Note("Список не очень важных дел", "26.07.2021", "Иди на улицу, там ЛЕТО"))
        notes!!.add(Note("Список не важных дел", "26.07.2021", "Что бы ты не делал! лучше не будет! Будет по другому... "))
    }

    private fun showNoteLand(note: Note?) {
        val noteFragment: Fragment = newInstance(note!!)
        requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.landscapeContainer, noteFragment)
                .commit()
    }

    private fun showNotePort(note: Note?) {
        val noteFragment: Fragment = newInstance(note!!)
        requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerNoteList, noteFragment)
                .commit()
    }

    companion object {
        @JvmStatic
        fun newInstance(): NoteListFragment {
            return NoteListFragment()
        }
    }
}