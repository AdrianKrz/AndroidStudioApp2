package com.example.prm4.adapers




import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.prm4.databinding.CarImageBinding
import com.example.prm4.fragments.InfoFragment


class DishImageViewHolder(val binding: CarImageBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(resId: Int, isSelected: Boolean) {
        binding.image.setImageURI(InfoFragment.uri)
        binding.selectedFrame.visibility =
            if (isSelected) View.VISIBLE else View.INVISIBLE
    }
}

class ImagesAdapter : RecyclerView.Adapter<DishImageViewHolder>() {

    companion object {
        @JvmStatic
        var id: Int = 0
            private set(value) {
                field = value
            }

        @JvmStatic
        fun set(value: Int) {
            id = value
        }
    }





    private var selectedPosition: Int = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishImageViewHolder {
        val binding = CarImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DishImageViewHolder(binding)


    }

    override fun onBindViewHolder(holder: DishImageViewHolder, position: Int) {
        holder.bind(id, position == selectedPosition)
}

    override fun getItemCount(): Int = 1
}