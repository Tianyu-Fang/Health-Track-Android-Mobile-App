package com.example.assignment2

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController


class ChangePasswordFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //
        val btnChange = view.findViewById<Button>(R.id.change_button)

        btnChange.setOnClickListener {
            view.findNavController().navigate(R.id.loginFragment)
        }

        //forget
        val textForget = view.findViewById<TextView>(R.id.textForget)

        textForget.setOnClickListener {
            view.findNavController().navigate(R.id.forgetPasswordFragment)
        }
    }

}