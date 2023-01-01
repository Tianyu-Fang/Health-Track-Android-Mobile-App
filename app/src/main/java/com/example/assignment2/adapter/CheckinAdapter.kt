package com.example.assignment2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.R
import com.example.assignment2.model.CheckinModel

class CheckinAdapter(
    private val dataList: ArrayList<CheckinModel>
) : RecyclerView.Adapter<CheckinAdapter.DataViewHolder>() {

    inner class DataViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val dataTitle = itemView.findViewById<TextView>(R.id.checkinItem)
        val data = itemView.findViewById<TextView>(R.id.checkinData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : DataViewHolder {
        val parentView = LayoutInflater.from(parent.context)
            .inflate(R.layout.checkin_card_layout, parent, false)

        return DataViewHolder(parentView)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val checkinModel: CheckinModel = dataList.get(position)
        holder.dataTitle.setText(checkinModel.title.toString())
        holder.data.setText(checkinModel.data.toString())
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}