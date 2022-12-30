package com.example.assignment2

import android.content.Intent
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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class CheckinFragment : Fragment() {
    val TAG = "GetCheckin"
    val TAG2 = "Debug1"
    var sharedReport :String = " "
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
        mActivity.supportActionBar?.title = "Checkin Report"

        constructRecyclerView(view, this@CheckinFragment)
        val fab = view.findViewById<FloatingActionButton>(R.id.fab_checkin)
        fab.setOnClickListener {

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, sharedReport)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)}
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
            val count = records.size - 1
            val datalist = populateCheckinList(records[count].symptom,records[count].stress_level,
            records[count].treatments,records[count].health_factors)
            val checkinAdapter = CheckinAdapter(datalist)
            CheckinRecyclerView.adapter = checkinAdapter
            CheckinRecyclerView.layoutManager = linearLayoutManager
            Log.d(TAG, "${records[0].symptom}")
            val calendar: Calendar = Calendar.getInstance()
            val date: Date = calendar.getTime()
            var dayOfWeek = SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime())
            sharedReport = "Hi! This is my daily report on ${dayOfWeek}!" +
                    "\n Symtom: ${records[count].symptom}" +
                    "\n Stress level: ${records[count].stress_level}" +
                    "\n Treatments: ${records[count].treatments}" +
                    "\n Health factors: ${records[count].health_factors}"
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