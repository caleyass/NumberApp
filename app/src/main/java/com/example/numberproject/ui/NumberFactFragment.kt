package com.example.numberproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.numberproject.NumberApplication
import com.example.numberproject.data.Number
import com.example.numberproject.databinding.FragmentNumberFactBinding
import com.example.numberproject.databinding.FragmentStarterBinding
import com.example.numberproject.viewmodel.NumbersViewModel
import com.example.numberproject.viewmodel.NumbersViewModelFactory

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        createNumber()
        bind(number)
    }

    private fun createNumber(){
        var num : Int? = navigationArgs.number?.toInt()
        val fact = viewModel.getFact(num)
        if(num == null)
            num = fact.split(" ")[0].toInt()
        number = Number(0, number = num, fact = fact)
    }

    /**
     * @param number number that was entered in StarterFragment
     * Binds views according to entered number
     * */
    fun bind(number: Number){
        binding.number.text = number.number.toString()
        binding.numberFact.text = number.fact.toString()
    }
}