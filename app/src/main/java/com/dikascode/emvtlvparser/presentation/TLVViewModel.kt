package com.dikascode.emvtlvparser.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dikascode.emvtlvparser.domain.ParseTLVUseCase
import com.dikascode.emvtlvparser.domain.Result
import com.dikascode.emvtlvparser.model.TLV

class TLVViewModel : ViewModel() {

    private val parseTLVUseCase = ParseTLVUseCase()

    private val _tlvList = MutableLiveData<List<TLV>>()
    val tlvList: LiveData<List<TLV>> = _tlvList

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun parseTLVData(tlvData: String) {
        when (val result = parseTLVUseCase.execute(tlvData)) {
            is Result.Success -> _tlvList.value = result.data
            is Result.Error -> _error.value = result.message
        }
    }
}
