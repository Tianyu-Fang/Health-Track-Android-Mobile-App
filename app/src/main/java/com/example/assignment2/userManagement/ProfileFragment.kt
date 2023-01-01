package com.example.assignment2.userManagement

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.R
import com.example.assignment2.adapter.ProfileAdapter
import com.example.assignment2.model.ProfileModel
import com.example.assignment2.model.User
import com.example.assignment2.repository.AuthRepository
import com.example.assignment2.viewmodel.AuthViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ProfileFragment : Fragment() {

    private val viewModel: AuthViewModel by viewModels {
        AuthViewModel.Provider(AuthRepository.repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        constructRecyclerView(view, this@ProfileFragment)
        val btnLogout = view.findViewById<Button>(R.id.logOut_button)

        btnLogout.setOnClickListener {
            //after log out, remove the user_id data
            val sharedPreference =
                this.getActivity()?.getSharedPreferences("user", Context.MODE_PRIVATE)
            val editor = sharedPreference?.edit()
            if (editor != null) {
                editor.putString("user_id", null)
                editor.commit()
            }
            view.findNavController().navigate(R.id.loginFragment)
        }

        val btnUpdate = view.findViewById<Button>(R.id.update_button)
        btnUpdate.setOnClickListener {
            view.findNavController().navigate(R.id.profileUpdateFragment)
        }

        val btnSetting = view.findViewById<ImageView>(R.id.setting)
        btnSetting.setOnClickListener {
            view.findNavController().navigate(R.id.settingFragment)
        }

    }

    fun constructRecyclerView(view: View, context: ProfileFragment) {
        var db = Firebase.firestore
        val userDB = viewModel.getUserEmail()?.let { db.collection("User").document(it) }
        var user = User()
        if (userDB != null) {
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
                Log.d("1", "DocumentSnapshot data: ${document.data}")
                val userName = view.findViewById<TextView>(R.id.userName)
                val userEmail = view.findViewById<TextView>(R.id.userEmail)
                userName.setText(user.userName)
                userEmail.setText(user.userEmail)
                val ProfileAdapter = ProfileAdapter(populateCourseList(user))
                val courseRecyclerView = view.findViewById<RecyclerView>(R.id.profile_recyclerview)

                val linearLayoutManager = LinearLayoutManager(
                    getContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
                courseRecyclerView.layoutManager = linearLayoutManager
                courseRecyclerView.adapter = ProfileAdapter

            }
        }

//            val ProfileAdapter = ProfileAdapter(populateCourseList(viewModel.getUser()))
//            val courseRecyclerView = view.findViewById<RecyclerView>(R.id.profile_recyclerview)
//
//            val linearLayoutManager = LinearLayoutManager(
//                getContext(),
//                LinearLayoutManager.VERTICAL,
//                false
//            )
//            courseRecyclerView.layoutManager = linearLayoutManager
//            courseRecyclerView.adapter = ProfileAdapter

    }

    fun populateCourseList(user: User): ArrayList<ProfileModel> {
        val dataList = ArrayList<ProfileModel>()
        dataList.add(ProfileModel(1, "Date of Birth", user.DoB))
        dataList.add(ProfileModel(2, "Gender", user.gender))
        dataList.add(ProfileModel(3, "Blood Type", user.bloodType))
        dataList.add(ProfileModel(4, "Height", user.height))
        dataList.add(ProfileModel(5, "Weight", user.weight))
        return dataList
    }

}