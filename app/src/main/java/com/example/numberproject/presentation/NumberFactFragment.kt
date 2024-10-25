package com.example.numberproject.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.numberproject.NumberApplication
import com.example.numberproject.data.local.entity.Number
import com.example.numberproject.databinding.FragmentNumberFactBinding
import com.example.numberproject.presentation.viewmodel.NumbersViewModel
import com.example.numberproject.presentation.viewmodel.NumbersViewModelFactory
import java.math.BigInteger

class NumberFactFragment : Fragment() {

    private val navigationArgs: NumberFactFragmentArgs by navArgs()
    private var _binding: FragmentNumberFactBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NumbersViewModel by activityViewModels {
        NumbersViewModelFactory(
            (activity?.application as NumberApplication).database.numberDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNumberFactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the entered number from the navigation arguments
        val num: Long? = navigationArgs.number?.toLong()

        // Trigger creation of Number object based on the provided number
        viewModel.createNumber(num)


        // Observe the LiveData in the ViewModel
        viewModel.number.observe(viewLifecycleOwner) { number ->
            // Bind the data to the UI when number is updated
            bind(number)
        }
    }

    /**
     * Inserts the Number object into the database
     */
//    private fun insertNumber(number: Number) {
//        viewModel.addNumber(number)
//    }

    /**
     * Binds the views according to the entered Number data
     */
    private fun bind(number: Number) {
        binding.number.text = number.number.toString()
        binding.numberFact.text = number.fact
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Prevent memory leaks
    }
}
