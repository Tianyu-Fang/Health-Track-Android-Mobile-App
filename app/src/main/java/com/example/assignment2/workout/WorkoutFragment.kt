package com.example.assignment2.workout

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.assignment2.R
import com.example.assignment2.adapter.WorkoutPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class WorkoutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_workout, container, false)


        val tabLayout = view?.findViewById<TabLayout>(R.id.tabLayout)
        val viewpager2 = view?.findViewById<ViewPager2>(R.id.viewPager)

        val viewPagerAdapter = WorkoutPagerAdapter(this)
        viewpager2?.adapter = viewPagerAdapter

        val titles = arrayOf("Workout Score", "Workout History")

        TabLayoutMediator(
            tabLayout!!,
            viewpager2!!
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = titles[position]
        }.attach()

        val mActivity = activity as AppCompatActivity
        mActivity.supportActionBar?.setDisplayShowHomeEnabled(false)
        mActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mActivity.supportActionBar?.title = "Workout"

        return view

    }
}