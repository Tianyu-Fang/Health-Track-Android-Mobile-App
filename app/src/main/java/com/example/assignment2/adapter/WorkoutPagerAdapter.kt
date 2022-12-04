package com.example.assignment2.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.assignment2.WorkoutFragment
import com.example.assignment2.WorkoutScoreFragment
import com.example.assignment2.WorkoutHistoryFragment
import com.example.assignment2.measurement.MeasurementFragment

class WorkoutPagerAdapter(fa: WorkoutFragment): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> WorkoutScoreFragment()
            1 -> WorkoutHistoryFragment()
            else -> WorkoutScoreFragment()
        }
    }
}