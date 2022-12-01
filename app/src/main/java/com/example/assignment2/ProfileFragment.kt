package com.example.assignment2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        constructRecyclerView(view,this@ProfileFragment)
        val btnLogout = view.findViewById<Button>(R.id.logOut_button)

        btnLogout.setOnClickListener {
            view.findNavController().navigate(R.id.loginFragment)
        }

        val btnSetting = view.findViewById<ImageView>(R.id.setting)
        btnSetting.setOnClickListener{
            view.findNavController().navigate(R.id.settingFragment)
        }

    }

    fun constructRecyclerView(view: View, context: ProfileFragment) {
        val ProfileAdapter = ProfileAdapter(populateCourseList())

        val courseRecyclerView = view.findViewById<RecyclerView>(R.id.profile_recyclerview)

        val linearLayoutManager = LinearLayoutManager(
            getContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

//    val gridLayoutManager = GridLayoutManager(
//        context,
//        2,
//        RecyclerView.VERTICAL,
//        false
//    )
        courseRecyclerView.layoutManager = linearLayoutManager
        courseRecyclerView.adapter = ProfileAdapter
    }

    fun populateCourseList(): ArrayList<ProfileModel> {
        val courseList = ArrayList<ProfileModel>()
        courseList.add(
            ProfileModel(1, "PE3", "Good subject")
        )
        courseList.add(
            ProfileModel(2, "IoT", "Very good subject")
        )
        courseList.add(
            ProfileModel(3, "PE4", "Good subject")
        )
        courseList.add(
            ProfileModel(4, "IoT2", "Very good subject")
        )
        courseList.add(
            ProfileModel(5, "PE5", "Good subject")
        )
        courseList.add(
            ProfileModel(6, "IoT3", "Very good subject")
        )

        return courseList
    }

}