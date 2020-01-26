package com.erikriosetiawan.globofly.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.erikriosetiawan.globofly.R
import com.erikriosetiawan.globofly.databinding.ActivityDestinationDetailBinding
import com.erikriosetiawan.globofly.helpers.SampleData
import com.erikriosetiawan.globofly.models.Destination
import com.erikriosetiawan.globofly.services.DestinationService
import com.erikriosetiawan.globofly.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DestinationDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDestinationDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_destination_detail)

        setSupportActionBar(binding.detailToolbar)
        // Show the Up button in the action bar.
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val bundle: Bundle? = intent.extras

        if (bundle?.containsKey(ARG_ITEM_ID)!!) {
            val id = intent.getIntExtra(ARG_ITEM_ID, 0)

            loadDetails(id)

            initUpdateButton(id)

            initDeleteButton(id)
        }
    }

    private fun loadDetails(id: Int) {

        // To be replaced by retrofit code
//        val destination = SampleData.getDestinationsById(id)

        val destinationService = ServiceBuilder.buildService(DestinationService::class.java)
        val requestCall = destinationService.getDestination(id)

        requestCall.enqueue(object : Callback<Destination> {
            override fun onResponse(call: Call<Destination>, response: Response<Destination>) {
                if (response.isSuccessful) {
                    val destination = response.body()
                    destination?.let {
                        binding.etCity.setText(destination.city)
                        binding.etDescription.setText(destination.description)
                        binding.etCountry.setText(destination.country)

                        binding.collapsingToolbar.title = destination.city
                    }
                } else {
                    Toast.makeText(
                        this@DestinationDetailActivity,
                        "Failed to retrieve details",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<Destination>, t: Throwable) {
                Toast.makeText(
                    this@DestinationDetailActivity,
                    "Failed to retrieve details $t",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    private fun initUpdateButton(id: Int) {
        binding.btnUpdate.setOnClickListener {

            val city = binding.etCity.text.toString()
            val description = binding.etDescription.text.toString()
            val country = binding.etCountry.text.toString()

            val destinationService = ServiceBuilder.buildService(DestinationService::class.java)
            val requestCall = destinationService.updateDestination(id, city, description, country)

            requestCall.enqueue(object : Callback<Destination> {

                override fun onResponse(call: Call<Destination>, response: Response<Destination>) {
                    if (response.isSuccessful) {
                        finish() // Move back to DestinationListActivity
                        var updateDestination = response.body() // Use it or ignore It
                    } else {
                        Toast.makeText(
                            this@DestinationDetailActivity,
                            "Failed to update item",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<Destination>, t: Throwable) {
                    Toast.makeText(
                        this@DestinationDetailActivity,
                        "Failed to update item",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }

    private fun initDeleteButton(id: Int) {
        binding.btnDelete.setOnClickListener {

            val destinationService = ServiceBuilder.buildService(DestinationService::class.java)
            val requestCall = destinationService.deleteDestination(id)

            requestCall.enqueue(object : Callback<Unit> {

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful) {
                        finish() // Move back to DestinationListActivity
                        Toast.makeText(
                            this@DestinationDetailActivity,
                            "Successfully Deleted",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@DestinationDetailActivity,
                            "Failed to Delete",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Toast.makeText(
                        this@DestinationDetailActivity,
                        "Failed to Delete",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
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