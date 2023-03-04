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

    private val navigationArgs : NumberFactFragmentArgs by navArgs()

    private var _binding: FragmentNumberFactBinding? = null
    private val binding get() = _binding!!
    private val viewModel : NumbersViewModel by activityViewModels {
        NumbersViewModelFactory(
            (activity?.application as NumberApplication).database.numberDao()
        )
    }
    lateinit var number : Number


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
        createNumber()
        insertNumber()
        bind(number)
    }

    /**
     * Creates Number object according to entered number
     * */
    private fun createNumber(){
        var num : BigInteger? = navigationArgs.number?.toBigInteger() // num that was entered
        number = viewModel.createNumber(num) // create Number object according to entered number
    }

    /**
     * Inserts Number object into database
     * */
    private fun insertNumber() = viewModel.addNumber(number)


    /**
     * @param number number that was entered in StarterFragment
     * Binds views according to entered number
     * */
    fun bind(number: Number){
        binding.number.text = number.number.toString()
        binding.numberFact.text = number.fact.toString()
    }
}