package com.example.herbalplantsinsrilanka

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.herbalplantsinsrilanka.databinding.ItemLayoutBinding

class PlantAdapter(private val context: Context, private val plantList: MutableList<PlantItem>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<PlantAdapter.PlantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val currentItem = plantList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount() = plantList.size

    inner class PlantViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(plantItem: PlantItem) {
            binding.textPlantName.text = plantItem.plantName
            binding.textScientificName.text = plantItem.scientificName

            // Load image using Glide
            Glide.with(context)
                .load(plantItem.imageUrl)
                .placeholder(R.drawable.herb)
                .into(binding.imagePlant)

            // Set click listener for item click
            binding.root.setOnClickListener {
                listener.onItemClick(plantItem)
            }

            // Set long-click listener for item long press (to delete)
            binding.root.setOnLongClickListener {
                showDeleteConfirmationDialog(plantItem)
                true
            }
        }

        private fun showDeleteConfirmationDialog(plantItem: PlantItem) {
            AlertDialog.Builder(context)
                .setTitle("Delete Plant")
                .setMessage("Are you sure you want to delete ${plantItem.plantName}?")
                .setPositiveButton("Delete") { _, _ ->
                    // Delete the item from the list
                    val position = plantList.indexOf(plantItem)
                    if (position != -1) {
                        plantList.removeAt(position)
                        notifyItemRemoved(position)
                    }
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }

    interface OnItemClickListener {
        fun onItemClick(plantItem: PlantItem)
    }
}
