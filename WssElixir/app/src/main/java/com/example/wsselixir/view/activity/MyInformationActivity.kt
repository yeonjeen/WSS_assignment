package com.example.wsselixir.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wsselixir.databinding.ActivityMyinformationBinding

class MyInformationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyinformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyinformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setInformaionIntent()

    }
    private fun setInformaionIntent() {
        binding.tvMyInfoPutName.text = intent.getStringExtra("name")
        binding.tvMyInfoPutMBTI.text = intent.getStringExtra("mbti")

    }
}