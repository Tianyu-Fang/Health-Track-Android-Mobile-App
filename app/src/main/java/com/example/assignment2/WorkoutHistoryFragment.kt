package com.example.assignment2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.assignment2.databinding.FragmentWorkoutHistoryBinding
import com.example.assignment2.model.Checkin
import com.example.assignment2.model.Workout
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AAStyle
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class WorkoutHistoryFragment : Fragment() {

    private lateinit var binding: FragmentWorkoutHistoryBinding
    private var db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkoutHistoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db.collection("Workout")
            .get()
            .addOnSuccessListener { result ->
                var weekData = ArrayList<Workout>()
                for (document in result) {
                    val data =
                        Workout(
                            (document.data!!["goal"] as Number?)!!.toInt(),
                            (document.data!!["running_time"] as Number?)!!.toInt(),
                            (document.data!!["walking_time"] as Number?)!!.toInt(),
                            (document.data!!["other_time"] as Number?)!!.toInt()
                        )
                    weekData.add(data)
                    Log.d("workout history", "${document.id} => ${document.data}")
                }
                setupData(weekData)
            }
            .addOnFailureListener { exception ->
                Log.d("workout history", "Error getting documents: ", exception)
            }


        val fab = binding.fabWorkouthistory
        fab.setOnClickListener {

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "My weekly workout report is available!")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }


    private fun setupData(weekdata: ArrayList<Workout>) {
        val Mon = weekdata[1].running_time + weekdata[1].walking_time + weekdata[1].other_time
        binding.textView7.text = weekdata[0].running_time.toString()
        val Tue = weekdata[5].running_time + weekdata[5].walking_time + weekdata[5].other_time
        val Wed = weekdata[6].running_time + weekdata[6].walking_time + weekdata[6].other_time
        val Thu = weekdata[4].running_time + weekdata[4].walking_time + weekdata[4].other_time
        val Fri = weekdata[0].running_time + weekdata[0].walking_time + weekdata[0].other_time
        val Sat = weekdata[2].running_time + weekdata[2].walking_time + weekdata[2].other_time
        val Sun = weekdata[3].running_time + weekdata[3].walking_time + weekdata[3].other_time
        val aaChartModel: AAChartModel = AAChartModel()
            .legendEnabled(true)
            .yAxisVisible(false)
            .chartType(AAChartType.Area)
            .backgroundColor("#FFFFFF")
            .categories(arrayOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"))
            .series(
                arrayOf(
                    AASeriesElement()
                        .name("Weekly Workout Time")
                        .data(arrayOf(Mon, Tue, Wed, Thu, Fri, Sat, Sun))
                )
            )
        aaChartModel.colorsTheme(arrayOf("#7e9df4", "#a5bbf8", "#cbd859", "#FFD700"))
        //draw diagram
        binding.chartview1.aa_drawChartWithChartModel(aaChartModel)


    }
}
