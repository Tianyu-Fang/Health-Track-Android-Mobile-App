package com.example.assignment2.measurement

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.assignment2.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MeasurementFragment : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mActivity = activity as AppCompatActivity
        mActivity.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        mActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mActivity.supportActionBar?.title = "Health Measurements"
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_measurement, container, false)
        val view: View = inflater.inflate(R.layout.fragment_measurement, container, false)
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        val viewpager2 = view.findViewById<ViewPager2>(R.id.viewpager2)
        val viewPagerAdapter = ViewPagerAdapter(this)
        viewpager2.adapter = viewPagerAdapter

        val titles = arrayOf("View", "Edit")

        TabLayoutMediator(
            tabLayout,
            viewpager2
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = titles[position]
        }.attach()
        return view
    }


}