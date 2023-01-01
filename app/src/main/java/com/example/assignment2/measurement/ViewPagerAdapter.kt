package com.example.assignment2.measurement

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fa: MeasurementFragment) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ViewMeasurementFragment.newInstance("", "")
            1 -> EditMeasurementFragment.newInstance("", "")
            else -> ViewMeasurementFragment.newInstance("", "")
        }

    }


}