package com.example.assignment2.journal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.assignment2.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.example.assignment2.databinding.FragmentJournalDisplayBinding
import com.example.assignment2.model.JournalModel
import com.example.assignment2.model.MeasurementModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [JournalDisplayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class JournalDisplayFragment : Fragment() {

    private var db = Firebase.firestore
    private lateinit var binding: FragmentJournalDisplayBinding

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        binding = FragmentJournalDisplayBinding.inflate(layoutInflater)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment JournalDisplayFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            JournalDisplayFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val docRef = db.collection("Journal")
        docRef.get().addOnSuccessListener { result ->
            val count = result.size() - 1
            val measurementDB = db.collection("Journal").document(count.toString())


            //val measurementDB = db.collection("Measurement").document("randomnumber")

            var data = JournalModel()



            measurementDB.get().addOnSuccessListener { document ->
                data =
                    JournalModel(
//                            document.id

                        document.data!!["activity"].toString(),
                        document.data!!["eat"].toString(),
                        document.data!!["feeling"].toString(),
                    )

                binding.activityTextField.text = "${data.activity}"
                binding.eatTextField.text = "${data.eat}"
                binding.feelingTextField.text = "${data.feeling}"


            }

        }
    }
}