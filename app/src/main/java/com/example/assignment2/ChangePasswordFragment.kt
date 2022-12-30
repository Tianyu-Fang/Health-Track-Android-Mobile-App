package com.example.assignment2

import android.content.ContentValues.TAG
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
import androidx.navigation.fragment.NavHostFragment
import com.example.assignment2.repository.AuthRepository
import com.example.assignment2.viewmodel.AuthViewModel
import com.google.android.material.textfield.TextInputEditText


class ChangePasswordFragment : Fragment() {

    private val viewModel: AuthViewModel by viewModels {
        AuthViewModel.Provider(AuthRepository.repository)
    }

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
        val originPassword = view.findViewById<TextInputEditText>(R.id.originInput)
        val newPassword = view.findViewById<TextInputEditText>(R.id.newPasswordInput)
        val confirmPassword = view.findViewById<TextInputEditText>(R.id.confirmPasswordInput
        )
        btnChange.setOnClickListener {
            var origin = originPassword.text.toString()
            var new: String = newPassword.text.toString()
            var confirm: String = confirmPassword.text.toString()
            //check if empty input exists
            if (TextUtils.isEmpty(new) || TextUtils.isEmpty(origin) || TextUtils.isEmpty(confirm)) {
                Toast.makeText(requireContext(), "Please enter password", Toast.LENGTH_LONG).show()
            }
            else {
                //check if origin password is correct
                viewModel.login(viewModel.getUserEmail(),origin).observe(viewLifecycleOwner){
                    if(!it){
                        Toast.makeText(requireActivity(),
                            "Wrong Origin Password",
                            Toast.LENGTH_SHORT).show()
                    }else{
                        if(new!=confirm){
                            Toast.makeText(requireActivity(),
                                "The two entered passwords do not match",
                                Toast.LENGTH_SHORT).show()
                        }
                        else{
                            if(new.length<6){
                                Toast.makeText(requireActivity(),
                                    "The password must be at 6 characters long",
                                    Toast.LENGTH_SHORT).show()
                            }
                            else {
                                viewModel.updatePassword(new).observe(viewLifecycleOwner){
                                if(it) {

                                    Toast.makeText(
                                        requireContext(),
                                        "Password changes successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    view.findNavController().navigate(R.id.loginFragment)
                                }
                                    else{
                                    Toast.makeText(requireContext(),
                                        "Password not changed",
                                        Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }
                        }
                    }
                }
//
            }
        }

        //forget
        val textForget = view.findViewById<TextView>(R.id.textForget)
        textForget.setOnClickListener {
            view.findNavController().navigate(R.id.forgetPasswordFragment)
        }
    }

}