package com.example.astonintensiv4.task_1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.astonintensiv4.R
import com.example.astonintensiv4.databinding.FragmentCBinding

class CFragment : Fragment() {

    private lateinit var binding: FragmentCBinding
    private var transmittedText: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transmittedText = if (savedInstanceState != null) {
            savedInstanceState.getString(ARG_TEXT)
        } else {
            arguments?.getString(ARG_TEXT)
        }
        binding.resultText.text = transmittedText

        binding.fragmentAButton.setOnClickListener {
            parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            parentFragmentManager.beginTransaction()
                .replace(R.id.first_activity_fragment, AFragment(), AFragment.FRAGMENT_TAG)
                .commit()
        }

        binding.fragmentDButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.first_activity_fragment, DFragment(), DFragment.FRAGMENT_TAG)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(ARG_TEXT, transmittedText)
    }

    companion object {
        const val FRAGMENT_TAG = "fragment_c"
        private const val ARG_TEXT = "transmitted_text"

        fun newInstance(text: String): CFragment {
            val fragment = CFragment()
            val args = Bundle().apply {
                putString(ARG_TEXT, text)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
