package com.example.assignment2.measurement

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import com.example.assignment2.R
import com.example.assignment2.model.MeasurementModel
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass. Use the
 * [EditMeasurementFragment.newInstance] factory
 * method to create an instance of this fragment.
 */
class EditMeasurementFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_measurement, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of this fragment using
         * the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EditMeasurementFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditMeasurementFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        var Height: Int
        val unit_height = view.findViewById<TextInputEditText>(R.id.unit_height_textfield)
//        unit_height.
        unit_height.setTextSize(TypedValue.COMPLEX_UNIT_SP, 7f)


//        var weight: Int
        val unit_weight = view.findViewById<TextInputEditText>(R.id.unit_weight_textfield)
        unit_weight.setTextSize(TypedValue.COMPLEX_UNIT_SP, 7f)

//        var glucose: Int
        val unit_glucose = view.findViewById<TextInputEditText>(R.id.unit_glucose_textfield)
        unit_glucose.setTextSize(TypedValue.COMPLEX_UNIT_SP, 7f)

//        var pressure: String
        val unit_pressure = view.findViewById<TextInputEditText>(R.id.unit_pressure_textfield)
        unit_pressure.setTextSize(TypedValue.COMPLEX_UNIT_SP, 7f)

//        var breathing:Int
        val unit_breating = view.findViewById<TextInputEditText>(R.id.unit_breathing_textfield)
        unit_breating.setTextSize(TypedValue.COMPLEX_UNIT_SP, 7f)

//        var oxygen: Int
        val unit_oxygen = view.findViewById<TextInputEditText>(R.id.unit_oxygen_textfield)
        unit_oxygen.setTextSize(TypedValue.COMPLEX_UNIT_SP, 7f)

//        var temperature: Int
        val unit_temperature = view.findViewById<TextInputEditText>(R.id.unit_temperature_textfield)
        unit_temperature.setTextSize(TypedValue.COMPLEX_UNIT_SP, 7f)

//        var pulse: Int
        val unit_pulse = view.findViewById<TextInputEditText>(R.id.unit_pulse_textfield)
        unit_pressure.setTextSize(TypedValue.COMPLEX_UNIT_SP, 7f)

        val btnMeasurement = view.findViewById<Button>(R.id.measure_button)

        val docRef = db.collection("Measurement")
        docRef.get().addOnSuccessListener { result ->
            val count = result.size()
            val measurementDB = db.collection("Measurement").document(count.toString())

            btnMeasurement.setOnClickListener {
                val height = unit_height.text.toString().toInt()
                val weight = unit_weight.text.toString().toInt()
                val glucose = unit_glucose.text.toString().toInt()
                val pressure = unit_pressure.text.toString()
                val breathing = unit_breating.text.toString().toInt()
                val oxygen = unit_oxygen.text.toString().toInt()
                val temperature = unit_temperature.text.toString().toInt()
                val pulse = unit_pulse.text.toString().toInt()


                val measurementData = MeasurementModel(
                    height,
                    weight,
                    glucose,
                    pressure,
                    breathing,
                    oxygen,
                    temperature,
                    pulse
                )
                measurementDB.set(measurementData)
                view.findNavController().navigate(R.id.viewMeasurementFragment_btm)
            }

//        val measurementDB = db.collection("Measurement").document(count)


        }


    }


}



