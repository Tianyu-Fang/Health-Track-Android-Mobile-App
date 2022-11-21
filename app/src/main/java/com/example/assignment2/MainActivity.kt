package com.example.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.profile)

        //constructRecyclerView(this@MainActivity)

        setContentView(R.layout.measurement)

        listView = findViewById(R.id.listView)
        var list = mutableListOf<MeasureModel>()
        list.add(MeasureModel("Blood Glucose Level", "mg/dL"))
        list.add(MeasureModel("Height", "cm"))
        list.add(MeasureModel("Weight", "kg"))
        list.add(MeasureModel("Breathing", "min"))
        list.add(MeasureModel("Blood Oxygen Saturation", "%"))
        list.add(MeasureModel("Body Temperature", "Celsius"))
        list.add(MeasureModel("Pulse Rate","min"))

//        listView.adapter = MyListAdapter(this,R.layout.row,list)
//        listView.setOnItemClickListener{parent, view, position, id ->
//
//            if (position==0){
//                Toast.makeText(this@MainActivity, "Item One",   Toast.LENGTH_SHORT).show()
//            }
//            if (position==1){
//                Toast.makeText(this@MainActivity, "Item Two",   Toast.LENGTH_SHORT).show()
//            }
//            if (position==2){
//                Toast.makeText(this@MainActivity, "Item Three", Toast.LENGTH_SHORT).show()
//            }
//            if (position==3){
//                Toast.makeText(this@MainActivity, "Item Four",  Toast.LENGTH_SHORT).show()
//            }
//            if (position==4){
//                Toast.makeText(this@MainActivity, "Item Five",  Toast.LENGTH_SHORT).show()
//            }
//
//
//        }
    }
}

fun constructRecyclerView(context: AppCompatActivity) {
    val ProfileAdapter = ProfileAdapter(populateCourseList())

    val courseRecyclerView = context.findViewById<RecyclerView>(R.id.profile_recyclerview)

    val linearLayoutManager = LinearLayoutManager(
        context,
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
