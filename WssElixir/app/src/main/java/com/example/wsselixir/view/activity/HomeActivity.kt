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
import com.example.wsselixir.databinding.ActivityHomeBinding
import com.example.wsselixir.view.model.HomeViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var followerAdapter: FollowersAdapter
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        setupUI()
    }

    private fun setupUI() {
        setupMBTISpinner()
        registerUserInfo()
        setupRecyclerView()
        observeFollowers()
        homeViewModel.fetchData()
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

    private fun registerUserInfo() {

        binding.btnHomeRegistration.setOnClickListener {
            val name = binding.etHomeName.text.toString()
            val mbti = binding.spinnerHomeMBTI.selectedItem.toString()

            val errorMessageResId = homeViewModel.registerUserInfo(name, mbti)

            if (errorMessageResId != 0) {
                Toast.makeText(this, errorMessageResId, Toast.LENGTH_SHORT).show()
            } else {
                val intent =
                    Intent(this@HomeActivity, DetailActivity::class.java)
                startActivity(intent)
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
        homeViewModel.followers.observe(this) { followers ->
            followerAdapter.setUsers(followers)
        }
    }


}