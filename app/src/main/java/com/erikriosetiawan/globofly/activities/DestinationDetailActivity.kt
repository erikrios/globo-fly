package com.erikriosetiawan.globofly.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.erikriosetiawan.globofly.R
import com.erikriosetiawan.globofly.databinding.ActivityDestinationDetailBinding
import com.erikriosetiawan.globofly.helpers.SampleData
import com.erikriosetiawan.globofly.models.Destination

class DestinationDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDestinationDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_destination_detail)
    }

    private fun loadDetails(id: Int) {

        // To be replaced by retrofit code
        val destination = SampleData.getDestinationsById(id)

        destination?.let {
            binding.etCity.setText(destination.city)
            binding.etDescription.setText(destination.description)
            binding.etCountry.setText(destination.country)

            binding.collapsingToolbar.title = destination.city
        }
    }

    private fun initUpdateButton(id: Int) {
        binding.btnUpdate.setOnClickListener {

            val city = binding.etCity.text.toString()
            val description = binding.etDescription.text.toString()
            val country = binding.etCountry.text.toString()

            // To be replaced by retrofit code
            val destination = Destination()
            destination.id = id
            destination.city = city
            destination.description = description
            destination.country = country

            SampleData.updateDestination(destination)
            finish() // Move back to DestinationListActivity
        }
    }

    private fun initDeleteButton(id: Int) {
        binding.btnDelete.setOnClickListener {

            // To be replaced by retrofit code
            SampleData.deleteDestination(id)
            finish() // Move back to DestinationListActivity
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            navigateUpTo(Intent(this, DestinationListActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val ARG_ITEM_ID = "item_id"
    }
}