package com.dikascode.emvtlvparser.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dikascode.emvtlvparser.domain.ParseTLVUseCase
import com.dikascode.emvtlvparser.model.TLV
import com.dikascode.emvtlvparser.model.TagRegistry

class TLVViewModel : ViewModel() {

    private val parseTLVUseCase = ParseTLVUseCase()

    private val _tlvList = MutableLiveData<List<TLV>>()
    val tlvList: LiveData<List<TLV>> = _tlvList

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun parseTLVData(tlvData: String) {
        try {
            val parsedData = parseTLVUseCase.execute(tlvData)
            _tlvList.value = parsedData
        } catch (e: Exception) {
            _error.value = "Error parsing TLV data: ${e.message}"
        }
    }
}
