package com.example.assignment2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.adapter.CheckinAdapter
import com.example.assignment2.model.CheckinModel


class CheckinFragment : Fragment() {
    val TAG = "GetCheckin"
    val TAG2 = "Debug1"
    private val checkinViewModel: CheckinViewModel by viewModels {
        CheckinViewModelFactory((requireActivity().application as CheckinApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //support bar

        return inflater.inflate(R.layout.fragment_checkin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mActivity = activity as AppCompatActivity
        mActivity.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        mActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        constructRecyclerView(view, this@CheckinFragment)
    }

    fun constructRecyclerView(view: View, context: CheckinFragment) {


        val CheckinRecyclerView = view.findViewById<RecyclerView>(R.id.Checkin_recyclerview)

        val linearLayoutManager = LinearLayoutManager(
            getContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        checkinViewModel.allRecords.observe(requireActivity()) {
                records ->
            val datalist = populateCheckinList(records[0].symptom,records[0].stress_level,
            records[0].treatments,records[0].health_factors)
            val checkinAdapter = CheckinAdapter(datalist)
            CheckinRecyclerView.adapter = checkinAdapter
            CheckinRecyclerView.layoutManager = linearLayoutManager

            Log.d(TAG, "${records[0].symptom}")
        }
    }



    fun populateCheckinList(symptom:String, stress_level: String,treatments:String,health_factors:String): ArrayList<CheckinModel> {

//        var record = checkinViewModel.allRecords.value!![0].symptom
//        Log.d(TAG, record)
        val CheckinList = ArrayList<CheckinModel>()
        CheckinList.add(
            CheckinModel(1, "Symptoms", symptom)
        )
        CheckinList.add(
            CheckinModel(2, "Stress Level", stress_level)
        )
        CheckinList.add(
            CheckinModel(3, "Treatments", treatments)
        )
        CheckinList.add(
            CheckinModel(4, "Health Factors", health_factors)
        )

        return CheckinList
    }
}