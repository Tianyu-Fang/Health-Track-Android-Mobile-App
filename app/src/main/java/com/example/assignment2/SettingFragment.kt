package com.example.assignment2

import android.graphics.Path.Direction
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceScreen


class SettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        //setPreferencesFromResource(R.xml.preferences, rootKey)
        addPreferencesFromResource(R.xml.preferences)
        val showValueListener = Preference.OnPreferenceClickListener {
            findNavController().navigate(R.id.changePasswordFragment)
            true
        }
        findPreference<PreferenceScreen>("reset_password")?.onPreferenceClickListener = showValueListener
        }

}