package com.example.assignment2.journal

import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.assignment2.R
import com.example.assignment2.model.JournalModel
import com.example.assignment2.model.MeasurementModel
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class JournalFragment : Fragment() {

    private var db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mActivity = activity as AppCompatActivity
        mActivity.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        mActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_journal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //        var activity:String
        val activity = view.findViewById<TextInputEditText>(R.id.activity_text_field)
        activity.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)

        //        var activity:String
        val eat = view.findViewById<TextInputEditText>(R.id.eat_text_field)
        eat.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)

        //        var activity:String
        val feeling = view.findViewById<TextInputEditText>(R.id.feeling_text_field)
        feeling.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)

        val btnJournal = view.findViewById<Button>(R.id.journal_button)

        val docRef = db.collection("Journal")
        docRef.get().addOnSuccessListener { result ->
            val count = result.size()
            val measurementDB = db.collection("Journal").document(count.toString())

            btnJournal.setOnClickListener {
                val activity = activity.text.toString()
                val eat = eat.text.toString()
                val feeling = feeling.text.toString()
                val journalData = JournalModel(activity, eat, feeling)
                measurementDB.set(journalData)
                view.findNavController().navigate(R.id.journalDisplayFragment_btm)
            }


        }


    }


}