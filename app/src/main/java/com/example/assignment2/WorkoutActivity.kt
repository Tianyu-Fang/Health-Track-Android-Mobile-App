package com.example.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.assignment2.adapter.WorkoutPagerAdapter
import com.example.assignment2.databinding.ActivityWorkoutBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class WorkoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWorkoutBinding

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWorkoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()
        setupTabLayout()
    }

    private fun setupTabLayout() {

        val titles = arrayOf("Workout Score","Workout History")

        TabLayoutMediator(
            binding.tabLayout, binding.viewPager
        ) {
            tab: TabLayout.Tab, position: Int ->
            tab.text = titles[position]
        }.attach() //change the name of tabs
    }

    private fun setupViewPager() {
        val adapter = WorkoutPagerAdapter(this, 2)
        binding.viewPager.adapter = adapter
    }

    // https://developer.android.com/develop/ui/views/animations/screen-slide-2
    override fun onBackPressed() {
        viewPager = binding.viewPager
        if (viewPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }
}