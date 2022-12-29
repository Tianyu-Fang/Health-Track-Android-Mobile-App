package com.example.assignment2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.appcompat.view.menu.MenuBuilder
import androidx.core.view.MenuCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

class DashboardFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val mActivity = activity as AppCompatActivity
        mActivity.supportActionBar?.title = "Home"

        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

}