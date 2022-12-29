package com.example.assignment2.measurement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.assignment2.R
import com.example.assignment2.model.MeasurementModel
import com.example.assignment2.databinding.FragmentHealthScoreBinding
import com.example.assignment2.databinding.FragmentViewMeasurementBinding
import com.google.common.primitives.UnsignedBytes.toInt
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HealthScoreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HealthScoreFragment : Fragment() {

    private lateinit var binding: FragmentHealthScoreBinding
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
        binding = FragmentHealthScoreBinding.inflate(layoutInflater)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HealthScoreFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HealthScoreFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val docRef = db.collection("Measurement")
        docRef.get().addOnSuccessListener { result ->
            val count = result.size()-1
            val measurementDB = db.collection("Measurement").document(count.toString())


            //val measurementDB = db.collection("Measurement").document("randomnumber")

            var data = MeasurementModel()



            measurementDB.get().addOnSuccessListener { document ->
                data =
                    MeasurementModel(
//                            document.id
                        (document.data!!["height"] as Number?)!!.toInt(),
                        (document.data!!["weight"] as Number?)!!.toInt(),
                        (document.data!!["glucose"] as Number?)!!.toInt(),
                        document.data!!["pressure"].toString(),
                        (document.data!!["breathing"] as Number?)!!.toInt(),
                        (document.data!!["oxygen"] as Number?)!!.toInt(),
                        (document.data!!["temperature"] as Number?)!!.toInt(),
                        (document.data!!["pulse"] as Number?)!!.toInt(),

                        )
                //get height score
                var heightScore = (data.height * 0.55)
                //get valid height score
                if(heightScore>100)
                    heightScore = 200-heightScore

                var weightScore = data.weight * 0.9
                if(weightScore > 100)
                    weightScore = 200 - weightScore

                var heightWeightScore = ((heightScore + weightScore)/2).toInt()

                var glucoseScore = (data.glucose * 0.8).toInt()
                if(glucoseScore > 100)
                    glucoseScore = 200 - glucoseScore

                var pressureScore = 85

                var breathingScore = (data.breathing * 6).toInt()
                if(breathingScore > 100)
                    breathingScore = 200 - breathingScore

                var oxygenScore = (data.oxygen * 1.1).toInt()
                if(oxygenScore > 100)
                    oxygenScore = 200 - oxygenScore

                var temperatureScore = (data.temperature * 2.8).toInt()
                if(temperatureScore > 100)
                    temperatureScore = 200 - temperatureScore

                var pulseScore = (data.pulse * 1.2).toInt()
                if( pulseScore > 100)
                    pulseScore = 200 - pulseScore

                //binding
                binding.scoreHeightWeight.text = "$heightWeightScore"
                binding.scoreGlucose.text = "$glucoseScore"
                binding.scorePressure.text = "$pressureScore"
                binding.scoreBreathing.text = "$breathingScore"
                binding.scoreOxygen.text = "$oxygenScore"
                binding.scoreTemperature.text="$temperatureScore"
                binding.scorePulse.text = "$pulseScore"

            }
        }









    }
}