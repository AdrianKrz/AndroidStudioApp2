package com.example.prm4.photo.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.example.prm4.photo.Model.Settings
import com.example.prm4.R
import com.example.prm4.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentSettingsBinding.inflate(
            inflater, container, false
        ).also {
            binding = it
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.color.setOnCheckedChangeListener { _, _ ->  changeSettings()}

    }

    private fun idToColor(id: Int): Int =
        when (id) {
            R.id.black -> Color.BLACK
            R.id.white -> Color.WHITE
            R.id.green -> Color.GREEN
            else -> throw NotImplementedError()
        }




    private fun changeSettings(){
        val settings = Settings(
            idToColor(binding.color.checkedRadioButtonId),
            30.toFloat()
        )
        (parentFragmentManager.findFragmentByTag(PaintFragment::class.java.name) as? PaintFragment)?.setSettings(settings)
    }




}