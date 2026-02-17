package com.app.travelactivity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.travelactivity.R
import com.app.travelactivity.adapters.CountryAdapter
import com.app.travelactivity.data.TravelData
import com.app.travelactivity.databinding.FragmentTravelBinding

class TravelFragment : Fragment() {

    private var _binding: FragmentTravelBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTravelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar("Select Country")
        setupRecyclerView()
    }

    private fun setupToolbar(title: String) {
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar)
        activity.supportActionBar?.title = title
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(requireContext(), R.color.white))
    }

    private fun setupRecyclerView() {
        val adapter = CountryAdapter(TravelData.countries) { country ->
            // Navigate to attractions fragment
            val attractionsFragment = AttractionsFragment.newInstance(country.id)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, attractionsFragment)
                .addToBackStack(null)
                .commit()
        }
        
        binding.recyclerViewTravel.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewTravel.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
