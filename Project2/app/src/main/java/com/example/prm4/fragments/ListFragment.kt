package com.example.prm4.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.prm4.adapers.ThingAdapter
import com.example.prm4.Navigable
import com.example.prm4.adapers.ImagesAdapter
import com.example.prm4.data.ThingsDatabase
import com.example.prm4.data.model.ThingsEntity
import com.example.prm4.databinding.FragmentListBinding
import kotlin.concurrent.thread


class ListFragment : Fragment() {



    private lateinit var binding: FragmentListBinding
    private var adapter: ThingAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentListBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = (activity as? Navigable)?.let { ThingAdapter(it) }
        loadData()

        binding.list.let {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(requireContext())
        }


        binding.btAdd.setOnClickListener {
            (activity as? Navigable)?.navigate(Navigable.Destination.Add)
            InfoFragment.setImie("")
            InfoFragment.setOsiagniecia("")
            ImagesAdapter.set(0)
        }






    }


    fun loadData() =  thread {
        val kierowcas = ThingsDatabase.open(requireContext()).produkty.getAll().map { entity ->
            ThingsEntity(
                entity.id,
                entity.produkt,
                entity.adress.split("\n").toString(),
                entity.uri
            )
        }
        requireActivity().runOnUiThread {
            binding.numberOfElements.setText(kierowcas.size.toString())
        }

        adapter?.replace(kierowcas)

    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

}