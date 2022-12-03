package com.example.assignment2.measurement

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.assignment2.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MeasurementFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MeasurementFragment : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        ){
            tab: TabLayout.Tab, position: Int ->
            tab.text = titles[position]
        }.attach()
        return view
    }


}