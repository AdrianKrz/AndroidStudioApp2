package com.example.prm4.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.prm4.Navigable
import com.example.prm4.adapers.ImagesAdapter
import com.example.prm4.databinding.InfoBinding


class InfoFragment : Fragment() {



    private lateinit var binding: InfoBinding
    private lateinit var adapter: ImagesAdapter
    var i: Int = 0

    companion object {
        @JvmStatic
        var idaaa: Long = 0
            private set

        var imie: String = ""
            private set

        var osiagniecia: String = ""
            private set

        var icon: Int = 0
            private set

        var uri: Uri? = null
            private set

        @JvmStatic
        fun set(value: Long) {
            idaaa = value
        }
        @JvmStatic
        fun setImie(value: String) {
            imie = value
        }
        @JvmStatic
        fun setOsiagniecia(value: String) {
            osiagniecia = value
        }
        @JvmStatic
        fun setIcon(value: Int) {
            icon = value
        }
        @JvmStatic
        fun setUri(value: Uri) {
            uri = value
        }

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return InfoBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)


        adapter = ImagesAdapter()


        val xx : String = osiagniecia.substring(1, osiagniecia.length -1)
        binding.editTextText.setText(imie)
        binding.editTextText2.setText(xx)
        binding.imageInfo.setImageURI(uri)

        binding.buttonEdit.setOnClickListener {
            EditFragment.set(idaaa)

            (activity as? Navigable)?.navigate(Navigable.Destination.Add)
        }




    }

}