package com.dikascode.emvtlvparser.presentation

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dikascode.emvtlvparser.R
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

            // Validate input before parsing
            if (tlvData.trim().isEmpty()) {
                binding.parsedOutput.text = getString(R.string.please_enter_tlv_data)
            } else {
                tlvViewModel.parseTLVData(tlvData)
            }

            hideKeyboard(binding.tlvInput)
        }

        tlvViewModel.tlvList.observe(this) { tlvList ->
            val output = tlvList.joinToString("\n") { tlv ->
                "TAG: ${tlv.tag} | LENGTH: ${tlv.length} byte(s) | VALUE: ${tlv.value}"
            }
            binding.parsedOutput.text = output
        }

        tlvViewModel.error.observe(this) { errorMessage ->
            if (errorMessage != null) {
                binding.parsedOutput.text = errorMessage
            }
        }
    }

    private fun hideKeyboard(editText: EditText) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }
}
