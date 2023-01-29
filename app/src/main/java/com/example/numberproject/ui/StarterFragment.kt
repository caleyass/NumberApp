package com.example.numberproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.numberproject.NumberApplication
import com.example.numberproject.R
import com.example.numberproject.adapter.NumberListAdapter
import com.example.numberproject.data.Number
import com.example.numberproject.databinding.FragmentStarterBinding
import com.example.numberproject.viewmodel.NumbersViewModel
import com.example.numberproject.viewmodel.NumbersViewModelFactory


class StarterFragment : Fragment(R.layout.fragment_starter) {

    private var _binding: FragmentStarterBinding? = null
    private val binding get() = _binding!!
    private val viewModel : NumbersViewModel by activityViewModels {
        NumbersViewModelFactory(
            (activity?.application as NumberApplication).database.numberDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*if(savedInstanceState != null)
            binding?.enteredNumber?.editText?.setText(savedInstanceState?.getString("enteredTest"))
*/
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStarterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        val adapter = NumberListAdapter()
        binding.recyclerView.adapter = adapter
        viewModel.allNumbers.observe(this.viewLifecycleOwner){ numbers ->
            numbers.let{
                adapter.submitList(it)
            }
        }
        setButtons()
        super.onViewCreated(view, savedInstanceState)
    }

    /*override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("enteredTest", binding.enteredNumber.toString())
    }*/

    private fun setButtons(){
        setRandomFactButton()
        setFactButton()
    }

    /**
     * Sets click listener for GET RANDOM FACT button of entered number
     * */
    private fun setRandomFactButton() {
        binding.btnGetRandomFact.setOnClickListener {
            val action = StarterFragmentDirections.actionStarterFragmentToNumberFactFragment(null)
            findNavController().navigate(action)
        }
    }

    /**
     * Sets click listener for GET FACT button of entered number
     * */
    private fun setFactButton(){
        binding.btnGetFact.setOnClickListener {
            //gets entered number
            val enteredText : String = binding.enteredNumber.editText?.text.toString()
            if(numberIsEntered(enteredText)) {
//                val enteredNumber = enteredText.toInt()
                val action = StarterFragmentDirections.actionStarterFragmentToNumberFactFragment(enteredText)
                findNavController().navigate(action)
            }
        }
    }

    /**Checks whether user have entered the number*/
    private fun numberIsEntered(enteredText: String): Boolean {
        if(enteredText.isEmpty())
            Toast.makeText(context, "Input number!", Toast.LENGTH_LONG).show()
        return !enteredText.isEmpty()
    }


}