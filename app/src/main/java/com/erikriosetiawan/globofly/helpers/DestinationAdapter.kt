package com.erikriosetiawan.globofly.helpers

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erikriosetiawan.globofly.activities.DestinationDetailActivity
import com.erikriosetiawan.globofly.databinding.ListItemBinding
import com.erikriosetiawan.globofly.models.Destination

class DestinationAdapter(
    private val context: Context,
    private val destinations: List<Destination>
) : RecyclerView.Adapter<DestinationAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val itemBinding = ListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = destinations.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(destinations[position]) {
            val intent = Intent(context, DestinationDetailActivity::class.java)
            intent.putExtra(DestinationDetailActivity.ARG_ITEM_ID, destinations[position].id)
            context.startActivity(intent)
        }
    }

    inner class ViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindViews(destination: Destination, listener: (Destination) -> Unit) {
            binding.destination = destination
            binding.root.setOnClickListener { listener(destination) }
        }
    }
}