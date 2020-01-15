package com.erikriosetiawan.globofly

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.erikriosetiawan.globofly.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome)

        // To be replaced by retrofit code
        binding.message.text = getString(R.string.message)
        binding.button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.button.id -> {
                Toast.makeText(this, "Button clicked!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
