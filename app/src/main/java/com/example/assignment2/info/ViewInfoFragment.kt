package com.example.assignment2.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.assignment2.R
import me.relex.circleindicator.CircleIndicator3

class ViewInfoFragment : Fragment() {
    private val data = mutableListOf<String>()
    private val fragmentList = ArrayList<Fragment>()
    private lateinit var viewPager: ViewPager2
    private lateinit var indicator: CircleIndicator3 // https://android-arsenal.com/details/1/1268
    private lateinit var btnNext: Button
    private lateinit var btnBack: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mActivity = activity as AppCompatActivity
        mActivity.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        mActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mActivity.supportActionBar?.title = "Health Information"
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_view_info, container, false)
        viewPager = view.findViewById(R.id.view_pager2)
        indicator = view.findViewById(R.id.indicator)
        btnNext = view.findViewById(R.id.btn_next)
        btnBack = view.findViewById(R.id.btn_back)
        addFragmentToList()
        setupViewPager()
        return view
    }


    private fun setupViewPager() {
        // viewPager.adapter = ViewPagerAdapter(data, this)
        // https://developer.android.com/develop/ui/views/animations/vp2-migration
        viewPager.adapter = InfoViewPagerFragmentAdapter(requireActivity(), fragmentList)
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


    private fun addFragmentToList() {
        for (item in 1..3) {
            data.add("item $item")
        }
        //problem, InfoFragment
        fragmentList.add(InfoFragment())
        fragmentList.add(InfoVideoFragment())
        fragmentList.add(InfoExpertFragment())

    }

}