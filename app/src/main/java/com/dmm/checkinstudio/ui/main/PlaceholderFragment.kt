package com.dmm.checkinstudio.ui.main

import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.dmm.checkinstudio.CheckInViewModel
import com.dmm.checkinstudio.R
import com.dmm.checkinstudio.ViewModel.UsuarioViewModel
import com.dmm.checkinstudio.databinding.FragmentCrudBinding
import com.dmm.checkinstudio.databinding.FragmentMainBinding
import com.dmm.checkinstudio.entities.CheckIn
import com.dmm.checkinstudio.entities.Usuario
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

/**
 * A placeholder fragment containing a simple view.
 */
@AndroidEntryPoint
class PlaceholderFragment : Fragment(R.layout.fragment_main) {

    private val checkInViewModel: CheckInViewModel by viewModels()
    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // Define the button and imageview type variable
    //lateinit var camera_open_id: Button
    //lateinit var click_image_id: ImageView

/*    companion object {
        // Define the pic id
        private const val pic_id = 123
    }  */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)

        }
    }

    // This method will help to retrieve the image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Match the request 'pic id with requestCode
        if (requestCode == 123) {
            // BitMap is data structure of image file which store the image in memory
            val photo = data!!.extras!!["data"] as Bitmap?
            // Set the image in imageview for display
            binding.biometriaImage.setImageBitmap(photo)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
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
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //_binding = FragmentMainBinding.bind(view)

/*        checkInViewModel.checkinLiveData.observe(viewLifecycleOwner){usuario->
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

        val date = Calendar.getInstance().time
        val dateTimeFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        val date_hora = dateTimeFormat.format(date)

        binding.saveOrUpdateButton.setOnClickListener {
            submitData(
                binding.tokenText.text.toString(),
                date_hora,
                "Biometria"
            )

            // Create the camera_intent ACTION_IMAGE_CAPTURE it will open the camera for capture the image
            val camera_intent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            } else {
                TODO("VERSION.SDK_INT < CUPCAKE")
            }
            // Start the activity with camera_intent, and request pic id
            startActivityForResult(camera_intent, 123)
        }
    }

    private fun submitData(
        token: String,
        datahora: String,
        biometria: String
    ) {
        checkInViewModel.insertChecKIn(CheckIn(0, token, datahora, biometria))
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
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
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