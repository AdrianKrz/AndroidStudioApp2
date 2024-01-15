package com.example.prm4.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.prm4.Navigable
import com.example.prm4.adapers.ImagesAdapter
import com.example.prm4.data.ThingsDatabase
import com.example.prm4.data.model.ThingsEntity
import com.example.prm4.databinding.FragmentEditBinding
import kotlin.concurrent.thread


class EditFragment : Fragment(){

    private lateinit var binding: FragmentEditBinding
    private lateinit var adapter: ImagesAdapter

    companion object {
        @JvmStatic
        var idaaa: Long = 0
            private set(value) {
                field = value
            }
        @JvmStatic
        var adress: String = "s"
            private set(adre){
                field = adre
            }

        @JvmStatic
        var phot: Uri? = null
            private set(ss) {
                field = ss
            }

        @JvmStatic
        fun set(value: Long) {
            idaaa = value
        }
        @JvmStatic
        fun setAd(ad: String) {
            adress = ad
        }
        @JvmStatic
        fun setPhoto(value: Uri?) {
            phot = value
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentEditBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ImagesAdapter()

        binding.newPhoto.setImageURI(phot)

        if(!idaaa.equals(0)){
            if(!InfoFragment.osiagniecia.length.equals(0)){
                binding.dishName.setText(InfoFragment.imie)
                binding.ingredientsName.setText(InfoFragment.osiagniecia.substring(1, InfoFragment.osiagniecia.length -1))
                binding.newPhoto.setImageURI(InfoFragment.uri)
            }
        }

        binding.save.setOnClickListener {

            val newProdukt = ThingsEntity(
                produkt = binding.dishName.text.toString(),
                adress = binding.ingredientsName.text.toString(),
                uri = phot.toString()
            )
            setPhoto(null)

            thread {
                ThingsDatabase.open(requireContext()).produkty.addKierowca(newProdukt)
                (activity as? Navigable)?.navigate(Navigable.Destination.List)
            }

        }

        binding.button2.setOnClickListener {
            thread {
                val newKierowca = ThingsEntity(
                    idaaa,
                    produkt = binding.dishName.text.toString(),
                    adress = binding.ingredientsName.text.toString(),
                    uri = InfoFragment.uri.toString()
                )
                ThingsDatabase.open(requireContext()).produkty.updateKierowca(newKierowca)
                (activity as? Navigable)?.navigate(Navigable.Destination.List)
                idaaa = 0
                setPhoto(null)
            }



        }

        binding.lokalizacja.setOnClickListener {
            binding.ingredientsName.setText(adress)
        }

        binding.addPhoto.setOnClickListener {
            (activity as? Navigable)?.navigate(Navigable.Destination.Photo)
        }



    }




}