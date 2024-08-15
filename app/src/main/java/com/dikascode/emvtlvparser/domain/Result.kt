package com.dikascode.emvtlvparser.domain

import com.dikascode.emvtlvparser.model.TLV

sealed class Result {
    data class Success(val data: List<TLV>) : Result()
    data class Error(val message: String) : Result()
}
