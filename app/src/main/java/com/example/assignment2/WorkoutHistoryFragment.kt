package com.example.assignment2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.assignment2.databinding.FragmentWorkoutHistoryBinding
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AAStyle

class WorkoutHistoryFragment : Fragment() {

    private lateinit var binding: FragmentWorkoutHistoryBinding

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

        setupData()

        val fab = binding.fabWorkouthistory
        fab.setOnClickListener {

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)}
    }



    private fun setupData() {
        val aaChartModel : AAChartModel = AAChartModel()
            .legendEnabled(true)
            .yAxisVisible(false)
            .chartType(AAChartType.Area)
            .backgroundColor("#FFFFFF")
            .categories(arrayOf("Mon","Tue","Wed","Thu","Fri","Sat","Sun"))
            .series(arrayOf(
                AASeriesElement()
                    .name("Weekly Workout Time")
                    .data(arrayOf(7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2)))
            )
        aaChartModel.colorsTheme(arrayOf("#7e9df4","#a5bbf8","#cbd859","#FFD700"))
        //draw diagram
        binding.chartview1.aa_drawChartWithChartModel(aaChartModel)


    }
}
