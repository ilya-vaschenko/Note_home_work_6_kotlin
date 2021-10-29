package com.example.note_home_work_6

import android.widget.TextView
import android.os.Bundle
import com.example.note_home_work_6.NoteTextFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.note_home_work_6.R

class NoteTextFragment : Fragment() {
    var note: Note? = null
    private var textViewNoteName: TextView? = null
    private var textViewNoteDate: TextView? = null
    private var textViewNoteDescription: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            val name = requireArguments().getString(ARG_NAME)
            val date = requireArguments().getString(ARG_DATE)
            val description = requireArguments().getString(ARG_DESCRIPTION)
            note = Note(name, date, description)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_note_text, container, false)
        textViewNoteName = view.findViewById(R.id.textViewForName)
        textViewNoteDate = view.findViewById(R.id.textViewForDate)
        textViewNoteDescription = view.findViewById(R.id.textViewForDesc)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateText()
    }

    private fun updateText() {
        textViewNoteName!!.text = note!!.name
        textViewNoteDate!!.text = note!!.date
        textViewNoteDescription!!.text = note!!.description
    }

    companion object {
        private const val ARG_NAME = "name"
        private const val ARG_DATE = "date"
        private const val ARG_DESCRIPTION = "description"
        @JvmStatic
        fun newInstance(note: Note): NoteTextFragment {
            val args = Bundle()
            args.putString(ARG_NAME, note.name)
            args.putString(ARG_DATE, note.date)
            args.putString(ARG_DESCRIPTION, note.description)
            val fragment = NoteTextFragment()
            fragment.arguments = args
            return fragment
        }
    }
}