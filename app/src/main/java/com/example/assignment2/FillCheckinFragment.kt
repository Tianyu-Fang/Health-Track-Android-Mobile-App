package com.example.assignment2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.assignment2.model.Checkin
import com.example.assignment2.viewmodel.CheckinViewModel
import com.example.assignment2.viewmodel.CheckinViewModelFactory
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
       // val data = arrayOf("Java", "Python", "C++", "C#", "Angular", "Go")
       // val adapter = ArrayAdapter(requireActivity().applicationContext, R.layout.item_symptom, R.array.symptom_list)

        var symptom: String = "GG"
        val spinner = view.findViewById<Spinner>(R.id.sSymptom)
        //val symptom = spinner.selectedItem.toString()
        val adapter =ArrayAdapter.createFromResource(requireActivity(),R.array.symptom_list, android.R.layout.simple_spinner_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                symptom = spinner.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }


        val spinner2 = view.findViewById<Spinner>(R.id.sStress)
        var stressLevel : String ="GG"
        val adapter2 =ArrayAdapter.createFromResource(requireActivity(),R.array.stress_list, android.R.layout.simple_spinner_item)
        spinner2.adapter = adapter2
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                stressLevel = spinner2.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        val spinner3 = view.findViewById<Spinner>(R.id.sTreatment)
//        val treatment = spinner3.selectedItem.toString()
        var treatment : String ="GG"
        val adapter3 =ArrayAdapter.createFromResource(requireActivity(),R.array.treatment_list, android.R.layout.simple_spinner_item)
        spinner3.adapter = adapter3
        spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                treatment = spinner3.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        val spinner4 = view.findViewById<Spinner>(R.id.sHealth)
//        val health_factors = spinner4.selectedItem.toString()
        var health_factors : String ="GG"
        val adapter4 =ArrayAdapter.createFromResource(requireActivity(),R.array.healthfactor_list, android.R.layout.simple_spinner_item)
        spinner4.adapter = adapter4
        spinner4.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                health_factors = spinner4.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }


        btnCheckin.setOnClickListener {

            //add new check in data
            //val intent = Intent(requireActivity(), NewNoteActivity::class.java)
            //startActivity(intent)
            val checkindata = Checkin("admin@qq.com",symptom, stressLevel,treatment,health_factors)
            lifecycleScope.launch(Dispatchers.IO) {
                checkinViewModel.addCheckin(checkindata)
            }
            view.findNavController().navigate(R.id.checkinHistoryFragment_btm)

        }
    }


}

