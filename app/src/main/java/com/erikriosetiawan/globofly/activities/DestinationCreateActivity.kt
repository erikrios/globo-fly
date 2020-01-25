package com.erikriosetiawan.globofly.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.erikriosetiawan.globofly.R
import com.erikriosetiawan.globofly.databinding.ActivityDestinationCreateBinding
import com.erikriosetiawan.globofly.models.Destination
import com.erikriosetiawan.globofly.services.DestinationService
import com.erikriosetiawan.globofly.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

            val destinationService = ServiceBuilder.buildService(DestinationService::class.java)
            val requestCall = destinationService.addDestination(newDestination)

            requestCall.enqueue(object : Callback<Destination> {
                override fun onResponse(call: Call<Destination>, response: Response<Destination>) {
                    if (response.isSuccessful) {
                        finish() // Move back to DestinationListActivity
                        var newlyCratedDestination = response.body() // Use it or ignore it
                        Toast.makeText(
                            this@DestinationCreateActivity,
                            "Successfully Added",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@DestinationCreateActivity,
                            "Failed to add item",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<Destination>, t: Throwable) {
                    Toast.makeText(
                        this@DestinationCreateActivity,
                        "Failed to add item",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }
}
