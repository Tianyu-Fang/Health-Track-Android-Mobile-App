package com.example.assignment2.measurement

import android.icu.util.Measure
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment2.R
import com.example.assignment2.databinding.FragmentHeightBinding
import com.example.assignment2.model.Workout
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.example.assignment2.model.MeasurementModel


class HeightFragment : Fragment() {
    private lateinit var binding: FragmentHeightBinding
    private var db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mActivity = activity as AppCompatActivity
        mActivity.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        mActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mActivity.supportActionBar?.title = "Height Data"
        // Inflate the layout for this fragment
        binding = FragmentHeightBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        db.collection("Measurement")
            .get()
            .addOnSuccessListener { result ->
                var heightData = ArrayList<MeasurementModel>()
                for (document in result) {
                    val data =
                        MeasurementModel(
                            (document.data!!["height"] as Number?)!!.toInt(),
                            (document.data!!["weight"] as Number?)!!.toInt(),
                            (document.data!!["glucose"] as Number?)!!.toInt(),
                            document.data!!["pressure"].toString(),
                            (document.data!!["breathing"] as Number?)!!.toInt(),
                            (document.data!!["oxygen"] as Number?)!!.toInt(),
                            (document.data!!["temperature"] as Number?)!!.toInt(),
                            (document.data!!["pulse"] as Number?)!!.toInt(),
                        )
                    heightData.add(data)
                    Log.d("height data", "${document.id} => ${document.data}")
                }
                setupData(heightData)
            }
            .addOnFailureListener { exception ->
                Log.d("workout history", "Error getting documents: ", exception)
            }

    }


    private fun setupData(heightdata: ArrayList<MeasurementModel>) {
        val Jun = heightdata[0].height
        val Jul = heightdata[1].height
        val Aug = heightdata[2].height
        val Sep = heightdata[3].height
        val Oct = heightdata[4].height
        val Nov = heightdata[5].height
        val Dec = heightdata[6].height

        val aaChartModel: AAChartModel = AAChartModel()
            .legendEnabled(true)
            .yAxisVisible(true)
            .chartType(AAChartType.Area)
            .backgroundColor("#FFFFFF")
            .categories(arrayOf("Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"))
            .series(
                arrayOf(
                    AASeriesElement()
                        .name("Height")
                        .data(arrayOf(Jun, Jul, Aug, Sep, Oct, Nov, Dec))
                )
            )
        aaChartModel.colorsTheme(arrayOf("#7e9df4", "#a5bbf8", "#cbd859", "#FFD700"))
        //draw diagram
        binding.chartview2.aa_drawChartWithChartModel(aaChartModel)

    }

}