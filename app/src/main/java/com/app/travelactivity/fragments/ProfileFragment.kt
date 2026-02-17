package com.app.travelactivity.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.app.travelactivity.LoginActivity
import com.app.travelactivity.PreferencesHelper
import com.app.travelactivity.R
import com.app.travelactivity.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var preferencesHelper: PreferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferencesHelper = PreferencesHelper(requireContext())
        setupToolbar("Settings")
        setupViews()
    }

    private fun setupToolbar(title: String) {
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar)
        activity.supportActionBar?.title = title
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(requireContext(), R.color.white))
    }

    private fun setupViews() {
        val username = preferencesHelper.getCurrentUsername()
        binding.textViewUsername.text = "Logged in as: $username"
        
        binding.buttonLogout.setOnClickListener {
            preferencesHelper.logout()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
