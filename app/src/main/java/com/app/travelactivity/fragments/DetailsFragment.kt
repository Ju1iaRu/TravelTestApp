package com.app.travelactivity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.app.travelactivity.R
import com.app.travelactivity.data.TravelData
import com.app.travelactivity.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private var countryId: Int = 0
    private var attractionId: Int = 0

    companion object {
        private const val ARG_COUNTRY_ID = "country_id"
        private const val ARG_ATTRACTION_ID = "attraction_id"

        fun newInstance(countryId: Int, attractionId: Int): DetailsFragment {
            val fragment = DetailsFragment()
            val args = Bundle()
            args.putInt(ARG_COUNTRY_ID, countryId)
            args.putInt(ARG_ATTRACTION_ID, attractionId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            countryId = it.getInt(ARG_COUNTRY_ID)
            attractionId = it.getInt(ARG_ATTRACTION_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val attraction = TravelData.getAttractionById(countryId, attractionId)
        
        if (attraction != null) {
            setupToolbar(attraction.name)
            displayAttractionDetails(attraction)
        } else {
            setupToolbar("Details")
        }
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

    private fun displayAttractionDetails(attraction: com.app.travelactivity.models.Attraction) {
        binding.textViewAttractionName.text = attraction.name
        binding.textViewAttractionDescription.text = attraction.description
        // For now, we'll use a placeholder. In a real app, you'd load images from resources or URLs
        binding.imageViewAttraction.setImageResource(R.drawable.ic_launcher_foreground)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

