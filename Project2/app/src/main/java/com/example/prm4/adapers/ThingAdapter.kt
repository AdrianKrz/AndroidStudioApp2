package com.example.prm4.adapers

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.net.toUri
import androidx.core.os.HandlerCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.prm4.ThingCallback
import com.example.prm4.Navigable
import com.example.prm4.data.ThingsDatabase
import com.example.prm4.data.model.ThingsEntity
import com.example.prm4.databinding.ListItenBinding
import com.example.prm4.fragments.InfoFragment
import kotlin.concurrent.thread

class ThingViewHolder(val binding: ListItenBinding)
    : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("ResourceType")
    fun bind(produkt: ThingsEntity) {
        binding.name.text = produkt.produkt
        binding.ingridients.text = produkt.adress
        binding.imageView.setImageURI(produkt.uri.toUri())
    }


}

class ThingAdapter(private val navigable: Navigable) : RecyclerView.Adapter<ThingViewHolder>() {
    private val data = mutableListOf<ThingsEntity>()
    private val handler: Handler = HandlerCompat.createAsync(Looper.getMainLooper())



    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThingViewHolder {
        val binding = ListItenBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ThingViewHolder(binding).also { vh ->
            binding.root.setOnClickListener{
                InfoFragment.setUri(data[vh.absoluteAdapterPosition].uri.toUri())
                InfoFragment.set(data[vh.absoluteAdapterPosition].id)
                InfoFragment.setImie(data[vh.absoluteAdapterPosition].produkt)
                InfoFragment.setOsiagniecia(data[vh.absoluteAdapterPosition].adress)
                navigable.navigate(Navigable.Destination.Info)

            }
        }.also { vh ->
            binding.root.setOnLongClickListener {
                AlertDialog.Builder(parent.context)
                    .setTitle("Czy na pewno chcesz usunąć ten element?")
                    .setMessage("Ta operacja jest nieodwracalna.")
                    .setPositiveButton("Usuń") { dialog, _ ->
                        thread {
                            ThingsDatabase.open(parent.context).produkty.removeKierowca(data[vh.absoluteAdapterPosition])
                        }
                        navigable.navigate(Navigable.Destination.Add)
                        navigable.navigate(Navigable.Destination.List)

                        dialog.dismiss()
                    }
                    .setNegativeButton("Anuluj") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()

                true
            }

        }
    }


    override fun onBindViewHolder(holder: ThingViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun replace(newData: List<ThingsEntity>) {
        val callback = ThingCallback(data, newData)
        data.clear()
        data.addAll(newData)
        val result = DiffUtil.calculateDiff(callback)
        handler.post {
            result.dispatchUpdatesTo(this)
        }
    }

}