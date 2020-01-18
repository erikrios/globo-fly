package com.erikriosetiawan.globofly.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.erikriosetiawan.globofly.R
import com.erikriosetiawan.globofly.databinding.ActivityDestinationDetailBinding
import com.erikriosetiawan.globofly.helpers.SampleData

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

    companion object {
        const val ARG_ITEM_ID = "item_id"
    }
}