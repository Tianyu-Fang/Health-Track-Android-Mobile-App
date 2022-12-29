package com.example.assignment2

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import com.example.assignment2.R
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.assignment2.repository.AuthRepository
import com.example.assignment2.viewmodel.AuthViewModel
import com.google.android.material.textfield.TextInputEditText


class LoginFragment : Fragment() {
    private val viewModel: AuthViewModel by viewModels {
        AuthViewModel.Provider(AuthRepository.repository)
    }

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
        val btnLogin =  view.findViewById<Button>(R.id.login_btn)
        val email_edt_text = view.findViewById<TextInputEditText>(R.id.email_edt_text)
        val pass_edt_text = view.findViewById<TextInputEditText>(R.id.pass_edt_text)

        btnLogin.setOnClickListener {
            val email = email_edt_text.getText().toString()
            val password = pass_edt_text.getText().toString()
            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                // Check if all fields are filled
                Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_LONG)
                    .show()
            } else {
                viewModel.login(email, password).observe(viewLifecycleOwner) {
                    if(!it) {
                        Toast.makeText(requireActivity(),
                            "Login Failed",
                            Toast.LENGTH_SHORT).show()

                    } else {
                        view.findNavController().navigate(R.id.profileFragment)

                        Toast.makeText(requireActivity(),
                            "Successfully Logged In",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }
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