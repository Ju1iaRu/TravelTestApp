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
import com.app.travelactivity.adapters.AttractionAdapter
import com.app.travelactivity.data.TravelData
import com.app.travelactivity.databinding.FragmentAttractionsBinding

class AttractionsFragment : Fragment() {

    private var _binding: FragmentAttractionsBinding? = null
    private val binding get() = _binding!!
    private var countryId: Int = 0

    companion object {
        private const val ARG_COUNTRY_ID = "country_id"

        fun newInstance(countryId: Int): AttractionsFragment {
            val fragment = AttractionsFragment()
            val args = Bundle()
            args.putInt(ARG_COUNTRY_ID, countryId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            countryId = it.getInt(ARG_COUNTRY_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAttractionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val country = TravelData.getCountryById(countryId)
        setupToolbar(country?.name ?: "Attractions")
        setupRecyclerView()
    }

    private fun setupToolbar(title: String) {
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar)
        activity.supportActionBar?.title = title
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        binding.toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun setupRecyclerView() {
        val country = TravelData.getCountryById(countryId)
        val attractions = country?.attractions ?: emptyList()
        
        val adapter = AttractionAdapter(attractions) { attraction ->
            // Navigate to details fragment
            val detailsFragment = DetailsFragment.newInstance(countryId, attraction.id)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, detailsFragment)
                .addToBackStack(null)
                .commit()
        }
        
        binding.recyclerViewAttractions.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewAttractions.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
