package com.dmm.checkinstudio.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dmm.checkinstudio.R
import com.dmm.checkinstudio.ViewModel.UsuarioViewModel
import com.dmm.checkinstudio.databinding.FragmentCrudBinding
import com.dmm.checkinstudio.entities.Usuario
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlaceholderFragmentCrud : Fragment(R.layout.fragment_crud) {

    //lateinit var binding: FragmentCrudBinding
    private val usuarioViewModel: UsuarioViewModel by viewModels()

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentCrudBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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

        _binding = FragmentCrudBinding.inflate(inflater, container, false)
        val root = binding.root

        val textView: TextView = binding.sectionLabel
        pageViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = "Tela de cadastro: " + it
            textView.text = "Tela de cadastro de usuários"
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCrudBinding.bind(view)

/*        usuarioViewModel.usuarioLiveData.observe(viewLifecycleOwner){usuario->
            if (usuario!!.size >=1){
                viewLifecycleOwner.lifecycleScope.launch {

                    binding.nameText.setText(usuario!![0].nome)
                    binding.documentText.setText(usuario!![0].documento.toString())
                    binding.tokenText.setText(usuario!![0].token.toString())
                }
            }else{
                Toast.makeText(requireContext(),"Usuario cadastrado", Toast.LENGTH_SHORT).show()
            }
        }*/


        binding.buttonAdicionarUsuario.setOnClickListener {
            submitData(
                binding.nameText.text.toString(),
                binding.documentText.text.toString(),
                binding.tokenText.text.toString()
            )
        }
    }

    private fun submitData(
        nome: String,
        documento: String,
        token: String
    ) {

        usuarioViewModel.insertUsuarioData(
            Usuario(
                id = 0,
                nome = nome,
                documento = documento,
                token = token)
        )

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
        fun newInstance(sectionNumber: Int): PlaceholderFragmentCrud {
            return PlaceholderFragmentCrud().apply {
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