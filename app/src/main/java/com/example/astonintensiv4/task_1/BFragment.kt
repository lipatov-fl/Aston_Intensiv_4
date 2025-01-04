package com.example.astonintensiv4.task_1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.example.astonintensiv4.R
import com.example.astonintensiv4.databinding.FragmentBBinding

class BFragment : Fragment() {

    private lateinit var binding: FragmentBBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentAButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.fragmentCButton.setOnClickListener {
            parentFragmentManager.commit {
                replace(
                    R.id.first_activity_fragment,
                    CFragment.newInstance(getString(R.string.transmitted_text))
                )
                setReorderingAllowed(true)
                addToBackStack(CFragment.FRAGMENT_TAG)
            }
        }
    }

    companion object {
        const val FRAGMENT_TAG = "fragment_b"
    }
}