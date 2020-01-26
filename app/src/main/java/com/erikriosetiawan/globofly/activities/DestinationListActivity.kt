package com.erikriosetiawan.globofly.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.erikriosetiawan.globofly.R
import com.erikriosetiawan.globofly.databinding.ActivityDestinationListBinding
import com.erikriosetiawan.globofly.helpers.DestinationAdapter
import com.erikriosetiawan.globofly.models.Destination
import com.erikriosetiawan.globofly.services.DestinationService
import com.erikriosetiawan.globofly.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DestinationListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDestinationListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_destination_list)

        setSupportActionBar(binding.toolBar)
        binding.toolBar.title

        binding.fab.setOnClickListener {
            val intent = Intent(this@DestinationListActivity, DestinationCreateActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        loadDestinations()
    }

    private fun loadDestinations() {

        // To be replaced by retrofit code
//        binding.destinyRecyclerView.adapter =
//            DestinationAdapter(binding.root.context, SampleData.DESTINATIONS)
        val destinationService = ServiceBuilder.buildService(DestinationService::class.java)

        val filter = HashMap<String, String>()
//        filter["country"] = "India"
//        filter["count"] = "1"

        val requestCall = destinationService.getDestinationList(filter)

//        requestCall.cancel()
//        requestCall.isCanceled

        requestCall.enqueue(object : Callback<List<Destination>> {

            // If you receive a HTTP Response, then this method is executed
            // Your status code will decide if your Http Response is Success or Error
            override fun onResponse(
                call: Call<List<Destination>>,
                response: Response<List<Destination>>
            ) {
                if (response.isSuccessful) {
                    // Your status code is in the range of 200 to 299
                    val destinationList = response.body()
                    binding.destinyRecyclerView.adapter =
                        destinationList?.let { DestinationAdapter(binding.root.context, it) }
                } else if (response.code() == 401) {
                    Toast.makeText(
                        this@DestinationListActivity,
                        "Your session has expired. Please login again.",
                        Toast.LENGTH_LONG
                    ).show()
                    // Your status code is in the range of 300's, 400's and 500's
                } else { // Application-level failure
                    Toast.makeText(
                        this@DestinationListActivity,
                        "Failed to retrieve items",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            // Invoked in case of Network Error or Establishing connection with Server
            // or Error creating Http Request or Error Processing Http Response
            override fun onFailure(call: Call<List<Destination>>, t: Throwable) {
                Toast.makeText(
                    this@DestinationListActivity,
                    "Error Occured $t",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}
