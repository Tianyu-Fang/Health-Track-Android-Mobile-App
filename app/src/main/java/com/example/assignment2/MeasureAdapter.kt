package com.example.assignment2
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import android.widget.TextView

class MeasureAdapter(var mCtx:Context, var resource:Int, var items:List<MeasureModel>)
    :ArrayAdapter<MeasureModel>(mCtx,resource,items)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)

        val view: View = layoutInflater.inflate(resource,null)
        var titleView: TextView = view.findViewById(R.id.measure_title)
        var unitView: TextView = view.findViewById(R.id.unit)

        var person:MeasureModel = items[position]

        titleView.text = person.title
        unitView.text = person.unit

        return view
    }


}