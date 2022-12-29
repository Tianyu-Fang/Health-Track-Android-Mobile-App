package com.example.assignment2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.assignment2.databinding.FragmentWorkoutScoreBinding
import com.example.assignment2.model.Workout
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class WorkoutScoreFragment: Fragment() {

    private lateinit var binding: FragmentWorkoutScoreBinding

    private var db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkoutScoreBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val workoutData = Workout(1000, 200, 300,
            200)

        val workoutDB = db.collection("Workout").document("randomnumber")

        workoutDB.set(workoutData)
        var data = Workout(1,1,1,1)

            workoutDB.get().addOnSuccessListener { document ->
                    data =
                    Workout(
//                            document.id
                        (document.data!!["goal"] as Number?)!!.toInt(),
                        (document.data!!["running_time"] as Number?)!!.toInt(),
                        (document.data!!["walking_time"] as Number?)!!.toInt(),
                        (document.data!!["other_time"] as Number?)!!.toInt()
                    )
                binding.walkingTV.text = "Walking: ${data.walking_time} minutes"
                binding.runningTV.text = "Running: ${data.running_time} minutes"
                binding.otherTV.text = "Other: ${data.other_time} minutes"
            }
//        binding.runningTV.text = "${data.running_time} minutes"
//        binding.otherTV.text = "${data.other_time} minutes"

    }

}