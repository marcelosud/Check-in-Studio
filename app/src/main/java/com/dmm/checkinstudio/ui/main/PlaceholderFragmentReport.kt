package com.dmm.checkinstudio.ui.main

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmm.checkinstudio.CheckInViewModel
import com.dmm.checkinstudio.R
import com.dmm.checkinstudio.ViewAdapter.MyRecyclerViewAdapter
import com.dmm.checkinstudio.ViewAdapter.ReportsAdapter
import com.dmm.checkinstudio.ViewModel.RelatorioViewModel
import com.dmm.checkinstudio.databinding.FragmentReportBinding
import com.dmm.checkinstudio.entities.CheckIn
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * A placeholder fragment containing a simple view.
 */
@AndroidEntryPoint
class PlaceholderFragmentReport : Fragment(R.layout.fragment_report)  {


    private lateinit var pageViewModel: PageViewModel
    lateinit var binding: FragmentReportBinding
    private val viewModel: RelatorioViewModel by viewModels()
    private lateinit var reportsAdapter: ReportsAdapter

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

        val binding = FragmentReportBinding.inflate(inflater, container, false)
        val root = binding.root

        val textView: TextView = binding.sectionLabel
        pageViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = "Tela de relatórios: "
        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentReportBinding.bind(view)
        initializeRecyclerView()
        getAllEntries()
    }

    private fun getAllEntries() {
        viewModel.allCheckInEntries.observe(viewLifecycleOwner){
            reportsAdapter.differ.submitList(it)
        }
    }

    private fun initializeRecyclerView() {
        reportsAdapter = ReportsAdapter()
        binding.rcvReports.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = reportsAdapter
        }

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
        fun newInstance(sectionNumber: Int): PlaceholderFragmentReport {
            return PlaceholderFragmentReport().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //_binding = null
    }
}