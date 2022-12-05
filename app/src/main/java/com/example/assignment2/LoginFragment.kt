package com.example.assignment2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mActivity = activity as AppCompatActivity
        mActivity.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        mActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //login
        val btnLogin = view.findViewById<Button>(R.id.login_btn)

        btnLogin.setOnClickListener {
            view.findNavController().navigate(R.id.profileFragment)
        }
        //forget password
        val textForget = view.findViewById<TextView>(R.id.forget)

        textForget.setOnClickListener {
            view.findNavController().navigate(R.id.forgetPasswordFragment)
        }

        //register
        val textreg = view.findViewById<TextView>(R.id.textSign)

        textreg.setOnClickListener {
            view.findNavController().navigate(R.id.registerFragment)
        }

    }



}