package com.erikriosetiawan.globofly.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.erikriosetiawan.globofly.R
import com.erikriosetiawan.globofly.databinding.ActivityDestinationListBinding
import com.erikriosetiawan.globofly.helpers.DestinationAdapter
import com.erikriosetiawan.globofly.helpers.SampleData

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
        binding.destinyRecyclerView.adapter =
            DestinationAdapter(binding.root.context, SampleData.DESTINATIONS)
    }
}
