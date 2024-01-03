package com.example.wsselixir.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wsselixir.R
import com.example.wsselixir.adapter.FollowersAdapter
import com.example.wsselixir.adapter.HomeViewModel
import com.example.wsselixir.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var followerAdapter: FollowersAdapter
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        setupMBTISpinner()
        registerInfo()
        setupRecyclerView()
        observeFollowers()
    }

    private fun setupMBTISpinner() {
        val mbtiSpinner: Spinner = binding.spinnerHomeMBTI

        val mbtiAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.typeMBTI,
            android.R.layout.simple_spinner_item,
        )
        mbtiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mbtiSpinner.adapter = mbtiAdapter
    }

    private fun registerInfo() {

        binding.btnHomeRegistration.setOnClickListener {
            val name = binding.etHomeName.text.toString()
            val mbti = binding.spinnerHomeMBTI.selectedItem.toString()

            val isNameBlank = name.isBlank()
            val isMbtiNull = mbti.isEmpty()

            when {
                isNameBlank && isMbtiNull -> {
                    Toast.makeText(this, R.string.allFailRegistration, Toast.LENGTH_SHORT).show()
                }

                isNameBlank -> {
                    Toast.makeText(this, R.string.nameFailRegistration, Toast.LENGTH_SHORT).show()
                }

                isMbtiNull -> {
                    Toast.makeText(this, R.string.mbtiFailRegistration, Toast.LENGTH_SHORT).show()
                }

                else -> {
                    val intent =
                        Intent(this@HomeActivity, MyInformationActivity::class.java).apply {
                            putExtra("name", name)
                            putExtra("mbti", mbti)
                        }
                    startActivity(intent)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        followerAdapter = FollowersAdapter(emptyList())
        binding.rvFollower.adapter = followerAdapter
        binding.rvFollower.layoutManager =
            GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false)
    }


    private fun observeFollowers() {
        viewModel.followers.observe(this) { followers ->
            followerAdapter.setUsers(followers)
        }
    }
}