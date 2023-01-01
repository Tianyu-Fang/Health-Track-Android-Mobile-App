package com.example.assignment2.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.assignment2.workout.WorkoutFragment
import com.example.assignment2.workout.WorkoutScoreFragment
import com.example.assignment2.workout.WorkoutHistoryFragment

class WorkoutPagerAdapter(fa: WorkoutFragment) : FragmentStateAdapter(fa) {
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