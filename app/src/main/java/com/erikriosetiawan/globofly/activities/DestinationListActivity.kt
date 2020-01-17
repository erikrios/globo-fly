package com.erikriosetiawan.globofly.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.erikriosetiawan.globofly.R
import com.erikriosetiawan.globofly.databinding.ActivityDestinationListBinding
import com.erikriosetiawan.globofly.helpers.DestinationAdapter

class DestinationListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDestinationListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_destination_list)

        setSupportActionBar(binding.toolBar)
        binding.toolBar.title

        binding.fab.setOnClickListener {
            // Intent code here
        }
    }

    private fun loadDestinations() {

        // To be replaced by retrofit code

    }
}
