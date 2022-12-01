package com.example.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //constructRecyclerView(this@MainActivity)
    }
}

//fun constructRecyclerView(context: ProfileFragment) {
//    val ProfileAdapter = ProfileAdapter(populateCourseList())
//
//    val courseRecyclerView = context.findViewById<RecyclerView>(R.id.profile_recyclerview)
//
//    val linearLayoutManager = LinearLayoutManager(
//        context,
//        LinearLayoutManager.VERTICAL,
//        false
//    )
//
////    val gridLayoutManager = GridLayoutManager(
////        context,
////        2,
////        RecyclerView.VERTICAL,
////        false
////    )
//    courseRecyclerView.layoutManager = linearLayoutManager
//    courseRecyclerView.adapter = ProfileAdapter
//}
//
//fun populateCourseList(): ArrayList<ProfileModel> {
//    val courseList = ArrayList<ProfileModel>()
//    courseList.add(
//        ProfileModel(1, "PE3", "Good subject")
//    )
//    courseList.add(
//        ProfileModel(2, "IoT", "Very good subject")
//    )
//    courseList.add(
//        ProfileModel(3, "PE4", "Good subject")
//    )
//    courseList.add(
//        ProfileModel(4, "IoT2", "Very good subject")
//    )
//    courseList.add(
//        ProfileModel(5, "PE5", "Good subject")
//    )
//    courseList.add(
//        ProfileModel(6, "IoT3", "Very good subject")
//    )
//
//    return courseList
//}