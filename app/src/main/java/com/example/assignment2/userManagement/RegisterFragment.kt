package com.example.assignment2.userManagement

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.assignment2.R
import com.example.assignment2.repository.AuthRepository
import com.example.assignment2.viewmodel.AuthViewModel
import com.google.android.material.textfield.TextInputEditText


class RegisterFragment : Fragment() {
    private val viewModel: AuthViewModel by viewModels {
        AuthViewModel.Provider(AuthRepository.repository)
    }

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
        val emailEdtText = view.findViewById<TextInputEditText>(R.id.reg_email)
        val nameEdtText = view.findViewById<TextInputEditText>(R.id.reg_name)
        val passEdtText = view.findViewById<TextInputEditText>(R.id.choosePassword)
        btnRegister.setOnClickListener {


            var email: String = emailEdtText.text.toString()
            var name: String = nameEdtText.text.toString()
            var password: String = passEdtText.text.toString()
            Log.d("ss", password)
            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(name)) {
                Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_LONG)
                    .show()
            } else {
//                viewModel.signUp(email, password)
                viewModel.signUp(email, password).observe(viewLifecycleOwner) {
                    if (it == true) {
                        Toast.makeText(
                            requireContext(),
                            "Successfully Registered",
                            Toast.LENGTH_LONG
                        ).show()
                        view.findNavController().navigate(R.id.loginFragment)
                        viewModel.addUserInfo(email, name)
                    } else {
                        Toast.makeText(requireContext(), "Registration Failed", Toast.LENGTH_LONG)
                            .show()

                    }
                }
            }
        }

        //login
        val textLogin = view.findViewById<TextView>(R.id.textLogin)

        textLogin.setOnClickListener {
            view.findNavController().navigate(R.id.loginFragment)
        }
    }

}