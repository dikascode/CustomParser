package com.dikascode.emvtlvparser.model

data class TLV(
    val tag: String,
    val length: Int,
    val value: String
)
