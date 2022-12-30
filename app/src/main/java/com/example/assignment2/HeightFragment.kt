package com.example.assignment2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment2.databinding.FragmentHeightBinding
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement


class HeightFragment : Fragment() {
    private lateinit var binding:FragmentHeightBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mActivity = activity as AppCompatActivity
        mActivity.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        mActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mActivity.supportActionBar?.title="Height Data"
        // Inflate the layout for this fragment
        binding =FragmentHeightBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupData()

    }

    private fun setupData() {
        val aaChartModel : AAChartModel = AAChartModel()
            .legendEnabled(true)
            .yAxisVisible(true)
            .chartType(AAChartType.Area)
            .backgroundColor("#FFFFFF")
            .categories(arrayOf("Mon","Tue","Wed","Thu","Fri","Sat","Sun"))
            .series(arrayOf(
                AASeriesElement()
                    .name("Height")
                    .data(arrayOf(170,171,172,173,174,175,176)))
            )
        aaChartModel.colorsTheme(arrayOf("#7e9df4","#a5bbf8","#cbd859","#FFD700"))
        //draw diagram
        binding.chartview2.aa_drawChartWithChartModel(aaChartModel)

    }

}