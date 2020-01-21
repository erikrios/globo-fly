package com.erikriosetiawan.globofly.activities

import android.content.Intent
import android.os.Bundle
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

        val requestCall = destinationService.getDestinationList()

        requestCall.enqueue(object : Callback<List<Destination>> {

            override fun onResponse(
                call: Call<List<Destination>>,
                response: Response<List<Destination>>
            ) {
                if (response.isSuccessful) {
                    val destinationList = response.body()
                    binding.destinyRecyclerView.adapter =
                        destinationList?.let { DestinationAdapter(binding.root.context, it) }
                }
            }

            override fun onFailure(call: Call<List<Destination>>, t: Throwable) {

            }
        })
    }
}
