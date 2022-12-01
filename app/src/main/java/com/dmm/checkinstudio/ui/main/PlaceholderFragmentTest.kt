package com.dmm.checkinstudio.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import android.widget.TextView
import android.widget.Toast
import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.dmm.checkinstudio.CheckInTesteViewModel
import com.dmm.checkinstudio.CheckInViewModel
import com.dmm.checkinstudio.databinding.FragmentTesteBinding
import com.dmm.checkinstudio.db.CheckInDatabase
import com.dmm.checkinstudio.db.CheckInRepository
import java.text.SimpleDateFormat
import java.util.*

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragmentTest : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentTesteBinding? = null

    //Vinculando ao view Model: CheckInViewModel
    //private lateinit var checkInViewModel: CheckInViewModel
    private val checkInViewModel: CheckInTesteViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding: FragmentTesteBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTesteBinding.inflate(inflater, container, false)
        val root = binding.root

        val textView: TextView = binding.sectionLabel
        pageViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = "Tela de Check-in: " + it
            textView.text = "Tela de Check-in: "
        })
        val textViewDate: TextView = binding.dateLabel
        pageViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = "Tela de Check-in: " + it
            val date = Calendar.getInstance().time
            val dateTimeFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
            textViewDate.text = dateTimeFormat.format(date)
        })


/*        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            checkInViewModel.saveOrUpdate()
            checkInViewModel.saveOrUpdateButtonText
            checkInViewModel.clearAllOrDeleteButtonText
        }*/


        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragmentTest {
            return PlaceholderFragmentTest().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}