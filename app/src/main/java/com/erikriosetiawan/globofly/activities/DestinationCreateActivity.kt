package com.erikriosetiawan.globofly.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.erikriosetiawan.globofly.R
import com.erikriosetiawan.globofly.databinding.ActivityDestinationCreateBinding
import com.erikriosetiawan.globofly.helpers.SampleData
import com.erikriosetiawan.globofly.models.Destination

class DestinationCreateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDestinationCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_destination_create)

        setSupportActionBar(binding.toolBar)

        // Show the Up Button in the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnAdd.setOnClickListener {
            val newDestination = Destination()
            newDestination.city = binding.etCity.text.toString()
            newDestination.description = binding.etDescription.text.toString()
            newDestination.country = binding.etCountry.text.toString()

            // To be replaced by retrofit code
            SampleData.addDestinations(newDestination)
            finish() // Move back to DestinationListActivity
        }
    }
}
