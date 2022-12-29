package com.example.assignment2

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.assignment2.repository.AuthRepository
import com.example.assignment2.viewmodel.AuthViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText


class ForgetPasswordFragment : Fragment() {

    private val viewModel: AuthViewModel by viewModels {
        AuthViewModel.Provider(AuthRepository.repository)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forget_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //register
        val btnSubmit = view.findViewById<Button>(R.id.submitButton)
        val emailEdtText = view.findViewById<TextInputEditText>(R.id.verifyEmailInput)

        btnSubmit.setOnClickListener {
            val email = emailEdtText.getText().toString()
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(
                    requireContext(),
                    "Please fill all the fields",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                viewModel.forgotPassword(email).observe(viewLifecycleOwner) {
                    if (it == true) {
                        Toast.makeText(
                            requireContext(),
                            "Successfully reset password",
                            Toast.LENGTH_SHORT
                        ).show()
                        view.findNavController().navigate(R.id.loginFragment)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Reset password failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }
        }

    }
}