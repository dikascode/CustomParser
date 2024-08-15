package com.dikascode.emvtlvparser.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.dikascode.emvtlvparser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val tlvViewModel: TLVViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.parseButton.setOnClickListener {
            val tlvData = binding.tlvInput.text.toString()
            tlvViewModel.parseTLVData(tlvData)
        }

        tlvViewModel.tlvList.observe(this) { tlvList ->
            val output = tlvList.joinToString("\n") { tlv ->
                "TAG: ${tlv.tag} | LENGTH: ${tlv.length} | VALUE: ${tlv.value}"
            }
            binding.parsedOutput.text = output
        }

        tlvViewModel.error.observe(this) { errorMessage ->
            if (errorMessage != null) {
                // Display the error message
                binding.parsedOutput.text = errorMessage
            }
        }
    }
}
