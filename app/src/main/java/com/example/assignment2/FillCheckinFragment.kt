package com.example.assignment2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.assignment2.model.Checkin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FillCheckinFragment : Fragment() {

    private val checkinViewModel: CheckinViewModel by viewModels {
        CheckinViewModelFactory((requireActivity().application as CheckinApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fill_checkin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mActivity = activity as AppCompatActivity
        mActivity.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        mActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val btnCheckin = view.findViewById<Button>(R.id.fillcheckin_button)

        // custom Spinner for symptoms
//       val data = arrayOf("Java", "Python", "C++", "C#", "Angular", "Go")
//       val adapter = ArrayAdapter(requireActivity().applicationContext, R.layout.item_symptom, data)
//       spinner.adapter = adapter
        val spinner = view.findViewById<Spinner>(R.id.sSymptom)
        val symptom = spinner.selectedItem.toString()

        val spinner2 = view.findViewById<Spinner>(R.id.sStress)
        val stress_level = Integer.parseInt(spinner2.selectedItem.toString())

        val spinner3 = view.findViewById<Spinner>(R.id.sTreatment)
        val treatment = spinner3.selectedItem.toString()

        val spinner4 = view.findViewById<Spinner>(R.id.sHealth)
        val health_factors = spinner4.selectedItem.toString()


        btnCheckin.setOnClickListener {

            //add new check in data
            //val intent = Intent(requireActivity(), NewNoteActivity::class.java)
            //startActivity(intent)
            val checkindata = Checkin(symptom, stress_level,treatment,health_factors)
            lifecycleScope.launch(Dispatchers.IO) {
                checkinViewModel.addNote(checkindata)
            }
            view.findNavController().navigate(R.id.checkinHistoryFragment_btm)

        }
    }
}