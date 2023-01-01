package com.example.assignment2

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.assignment2.databinding.FragmentWorkoutScoreBinding
import com.example.assignment2.model.Workout
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import java.text.SimpleDateFormat
import java.util.*

class WorkoutScoreFragment : Fragment() {

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
        var sharedPercentage = 0

        // get the weekday
        val calendar: Calendar = Calendar.getInstance()
        val date: Date = calendar.getTime()
        var dayOfWeek = SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime())
        // Wednesday
        //val workoutDB = db.collection("Workout").document("Wednesday")
        val workoutDB = db.collection("Workout").document(dayOfWeek)
        //val workoutData = Workout(1000, 50, 100, 50)
        //workoutDB.set(workoutData)
        var data = Workout(1, 1, 1, 1)


        val circularProgressBar = binding.totalScore

        workoutDB.get().addOnSuccessListener { document ->
            data =
                Workout(
//                    document.id
                    (document.data!!["goal"] as Number?)!!.toInt(),
                    (document.data!!["running_time"] as Number?)!!.toInt(),
                    (document.data!!["walking_time"] as Number?)!!.toInt(),
                    (document.data!!["other_time"] as Number?)!!.toInt()
                )
            binding.walkingTV.text = "Walking: ${data.walking_time} minutes"
            binding.runningTV.text = "Running: ${data.running_time} minutes"
            binding.otherTV.text = "Other: ${data.other_time} minutes"
            val goal = data.goal
            val finished = data.walking_time + data.running_time + data.other_time
            val percentage = finished * 100 / goal
            sharedPercentage = percentage
            binding.scorePercentTV.text = "+${percentage}%"

            circularProgressBar.apply {
                // Set Progress
                progress = (percentage as Number)!!.toFloat()
                // or with animation
                setProgressWithAnimation(percentage.toFloat(), 2000) // =2s

                // Set Progress Max
                progressMax = 100f

                // Set ProgressBar Color
                progressBarColor = Color.BLACK
                // or with gradient
                progressBarColorStart = Color.GRAY
                progressBarColorEnd = Color.RED
                progressBarColorDirection = CircularProgressBar.GradientDirection.TOP_TO_BOTTOM

                // Set background ProgressBar Color
                backgroundProgressBarColor = Color.GRAY
                // or with gradient
                backgroundProgressBarColorStart = Color.WHITE
                backgroundProgressBarColorEnd = Color.RED
                backgroundProgressBarColorDirection =
                    CircularProgressBar.GradientDirection.TOP_TO_BOTTOM

                // Set Width
                progressBarWidth = 7f // in DP
                backgroundProgressBarWidth = 3f // in DP

                // Other
                roundBorder = true
                startAngle = 180f
                progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT
            }

            binding.activityText.text = "${percentage}% hours \n of your daily goal"

        }
//        binding.runningTV.text = "${data.running_time} minutes"
//        binding.otherTV.text = "${data.other_time} minutes"

        val fab = binding.fab
        fab.setOnClickListener {

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT, "${sharedPercentage}% hours of my daily goal" +
                            " is achieved on ${dayOfWeek}!"
                )
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }


}