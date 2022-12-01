package com.example.assignment2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController


class RegisterFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //register
        val btnRegister = view.findViewById<Button>(R.id.register_button)

        btnRegister.setOnClickListener {
            view.findNavController().navigate(R.id.loginFragment)
        }

        //login
        val textLogin = view.findViewById<TextView>(R.id.textLogin)

        textLogin.setOnClickListener {
            view.findNavController().navigate(R.id.loginFragment)
        }
    }
}