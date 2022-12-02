package com.example.assignment2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.adapter.CheckinAdapter
import com.example.assignment2.model.CheckinModel


class CheckinFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_checkin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        constructRecyclerView(view, this@CheckinFragment)
    }

    fun constructRecyclerView(view: View, context: CheckinFragment) {
        val checkinAdapter = CheckinAdapter(populateCheckinList())

        val CheckinRecyclerView = view.findViewById<RecyclerView>(R.id.Checkin_recyclerview)

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
        CheckinRecyclerView.layoutManager = linearLayoutManager
        CheckinRecyclerView.adapter = checkinAdapter
    }

    fun populateCheckinList(): ArrayList<CheckinModel> {
        val CheckinList = ArrayList<CheckinModel>()
        CheckinList.add(
            CheckinModel(1, "PE3", "Good subject")
        )
        CheckinList.add(
            CheckinModel(2, "IoT", "Very good subject")
        )
        CheckinList.add(
            CheckinModel(3, "PE4", "Good subject")
        )
        CheckinList.add(
            CheckinModel(4, "IoT2", "Very good subject")
        )
        CheckinList.add(
            CheckinModel(5, "PE5", "Good subject")
        )
        CheckinList.add(
            CheckinModel(6, "IoT3", "Very good subject")
        )

        return CheckinList
    }
}