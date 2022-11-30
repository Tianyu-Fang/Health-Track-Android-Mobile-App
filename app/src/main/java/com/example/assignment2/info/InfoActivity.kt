package com.example.assignment2.info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.assignment2.R
import me.relex.circleindicator.CircleIndicator3

class InfoActivity : AppCompatActivity() {
    private val data = mutableListOf<String>()
    private val fragmentList = ArrayList<Fragment>()
    private lateinit var viewPager: ViewPager2
    private lateinit var indicator: CircleIndicator3 // https://android-arsenal.com/details/1/1268
    private lateinit var btnNext: Button
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        bindView()
    }

    private fun setupViewPager() {
        // viewPager.adapter = ViewPagerAdapter(data, this)
        // https://developer.android.com/develop/ui/views/animations/vp2-migration
        viewPager.adapter = InfoViewPagerFragmentAdapter(this, fragmentList)
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        indicator.setViewPager(viewPager)

        btnNext.setOnClickListener {
            viewPager.apply {
                // https://stackoverflow.com/questions/40891495/how-can-i-shake-viewpager-to-show-the-user-that-there-are-more-pages-available
                beginFakeDrag()
                fakeDragBy(-10f)
                endFakeDrag()
            }
        }

        btnBack.setOnClickListener {
            viewPager.apply {
                beginFakeDrag()
                fakeDragBy(10f)
                endFakeDrag()
            }
        }
    }



    private fun bindView() {

        viewPager = findViewById(R.id.view_pager2)
        indicator = findViewById(R.id.indicator)
        btnNext = findViewById(R.id.btn_next)
        btnBack = findViewById(R.id.btn_back)

    }

    private fun addFragmentToList() {
        for (item in 1..3) {
            data.add("item $item")
        }

        fragmentList.add(InfoFragment())
        fragmentList.add(InfoVideoFragment())
        fragmentList.add(InfoExpertFragment())

    }







}

