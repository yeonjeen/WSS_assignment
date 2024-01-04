package com.example.wsselixir.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wsselixir.adapter.DetailAdapter
import com.example.wsselixir.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val tabTitleArray = arrayOf(
        "사용자 정보",
        "팔로워 정보",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()
    }

    private fun setupViewPager(){
        val viewPager = binding.vpDetail
        val tabLayout = binding.tlDetail

        viewPager.adapter = DetailAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()
    }

}