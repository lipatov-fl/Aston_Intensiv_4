package com.example.astonintensiv4.task_1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.astonintensiv4.R
import com.example.astonintensiv4.databinding.FragmentDBinding

class DFragment : Fragment() {

    private lateinit var binding: FragmentDBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentBButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.first_activity_fragment, BFragment(), BFragment.FRAGMENT_TAG)
                .commit()
        }
    }

    companion object {
        const val FRAGMENT_TAG = "fragment_d"
    }

}