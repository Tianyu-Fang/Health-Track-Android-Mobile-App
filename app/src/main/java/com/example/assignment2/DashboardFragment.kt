package com.example.assignment2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.appcompat.view.menu.MenuBuilder
import androidx.core.view.MenuCompat
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.assignment2.databinding.FragmentDashboardBinding
import com.example.assignment2.model.MeasurementModel
import com.example.assignment2.model.User
import com.example.assignment2.model.Workout
import com.example.assignment2.repository.AuthRepository
import com.example.assignment2.viewmodel.AuthViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*


class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    private var db = Firebase.firestore
    private val viewModel: AuthViewModel by viewModels {
        AuthViewModel.Provider(AuthRepository.repository)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val mActivity = activity as AppCompatActivity
        mActivity.supportActionBar?.title = "Home"
        binding = FragmentDashboardBinding.inflate(layoutInflater)
        return binding.root
        //return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            val userDB = viewModel.getUserEmail()?.let { db.collection("User").document(viewModel.getUserEmail()!!) }
            var userName: String
            if (userDB != null) {
                userDB.get().addOnSuccessListener { document ->
                    userName = document.data!!["userName"].toString()
                    binding.dashboardName.text = userName
                }
            }

                val docRef = db.collection("Measurement")
                docRef.get().addOnSuccessListener { result ->
                    val count = result.size() - 1
                    val measurementDB = db.collection("Measurement").document(count.toString())
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

                binding.bloodpressure.text = "${data.pressure}"
                binding.breathing.text = "${data.breathing}"
                binding.bloodoxygen.text = "${data.oxygen}"
                binding.pulse.text = "${data.pulse}"
            }
        }

        val calendar: Calendar = Calendar.getInstance()
        val date: Date = calendar.getTime()
        var dayOfWeek = SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime())
        val workoutDB = db.collection("Workout").document(dayOfWeek)
        var data = Workout(1, 1, 1, 1)

        workoutDB.get().addOnSuccessListener { document ->
            data =
                Workout(
//                    document.id
                    (document.data!!["goal"] as Number?)!!.toInt(),
                    (document.data!!["running_time"] as Number?)!!.toInt(),
                    (document.data!!["walking_time"] as Number?)!!.toInt(),
                    (document.data!!["other_time"] as Number?)!!.toInt()
                )
            binding.walking.text = "${data.walking_time}"
            binding.running.text = "${data.running_time}"
            binding.exercise.text = "${data.other_time}"

        }

    }
}