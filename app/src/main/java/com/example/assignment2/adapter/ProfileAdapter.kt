package com.example.assignment2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.model.ProfileModel
import com.example.assignment2.R
import com.example.assignment2.model.User

class ProfileAdapter(
    private val dataList: ArrayList<ProfileModel>
): RecyclerView.Adapter<ProfileAdapter.DataViewHolder>(){

    inner class DataViewHolder(itemView: View):
            RecyclerView.ViewHolder(itemView){
                val dataTitle = itemView.findViewById<com.google.android.material.textfield.TextInputLayout>(
                    R.id.profileTitle
                )
                val data = itemView.findViewById<com.google.android.material.textfield.TextInputEditText>(
                    R.id.profileData
                )
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : DataViewHolder {
        val parentView = LayoutInflater.from(parent.context)
            .inflate(R.layout.userdata_layout, parent, false)

        return DataViewHolder(parentView)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        val courseModel: ProfileModel = dataList.get(position)
        holder.dataTitle.setHint(courseModel.title.toString())
        holder.data.setText(courseModel.data.toString())
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}
