package com.example.assignment2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.assignment2.model.User
import com.example.assignment2.repository.AuthRepository
import com.example.assignment2.viewmodel.AuthViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ProfileUpdateFragment : Fragment() {

    private val viewModel: AuthViewModel by viewModels {
        AuthViewModel.Provider(AuthRepository.repository)
    }

    private lateinit var email: String
    private lateinit var user : User
//    private lateinit var firebaseDatabase: FirebaseDatabase
//    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_profile_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayData(view)


        val uName = view.findViewById<TextInputEditText>(R.id.user_name)
        val blood_Type = view.findViewById<TextInputEditText>(R.id.blood_type)
        val gender_ = view.findViewById<TextInputEditText>(R.id.gender)
        val DoB_ = view.findViewById<TextInputEditText>(R.id.Date_of_Birth)
        val height_ = view.findViewById<TextInputEditText>(R.id.height)
        val weight_ = view.findViewById<TextInputEditText>(R.id.weight)

        val btnUpdate = view.findViewById<Button>(R.id.update_submit)
        val btnCancel = view.findViewById<Button>(R.id.cancel_submit)
        //update the database
        btnUpdate.setOnClickListener {

            user = User(viewModel.getUserEmail(), uName.getText().toString(),blood_Type.getText().toString(),gender_.getText().toString(),DoB_.getText().toString(),
                height_.getText().toString(),weight_.getText().toString())

            viewModel.updateUser(viewModel.getUserEmail(), user).observe(viewLifecycleOwner){
                if(it == true) {
                    Toast.makeText(requireContext(),
                        "Course Updated..",
                        Toast.LENGTH_SHORT)
                        .show()

                }
                else {
                    Toast.makeText(requireContext(),
                        "Fail to update course..",
                        Toast.LENGTH_SHORT).show()
                }
            }
            view.findNavController().navigate(R.id.profileFragment)
        }
        //cancel the update
        btnCancel.setOnClickListener {
            view.findNavController().navigate(R.id.profileFragment)
        }

    }

    fun displayData(view: View){
        var db = Firebase.firestore
        val userDB = db.collection("User").document(viewModel.getUserEmail())
        var user = User()
        userDB.get().addOnSuccessListener { document ->
            user =
                User(
                    document.data!!["userEmail"].toString(),
                    document.data!!["userName"].toString(),
                    document.data!!["bloodType"].toString(),
                    document.data!!["gender"].toString(),
                    document.data!!["doB"].toString(),
                    document.data!!["height"].toString(),
                    document.data!!["weight"].toString()
                )
            val userName = view.findViewById<TextInputEditText>(R.id.user_name)
            val bloodType = view.findViewById<TextInputEditText>(R.id.blood_type)
            val gender = view.findViewById<TextInputEditText>(R.id.gender)
            val DoB = view.findViewById<TextInputEditText>(R.id.Date_of_Birth)
            val height = view.findViewById<TextInputEditText>(R.id.height)
            val weight = view.findViewById<TextInputEditText>(R.id.weight)

            userName.setText(user.userName)
            bloodType.setText(user.bloodType)
            gender.setText(user.gender)
            DoB.setText(user.DoB)
            height.setText(user.height)
            weight.setText(user.weight)

        }

    }
}