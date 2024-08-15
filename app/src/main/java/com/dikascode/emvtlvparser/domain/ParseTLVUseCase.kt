package com.dikascode.emvtlvparser.domain

import com.dikascode.emvtlvparser.model.TLV
import com.dikascode.emvtlvparser.model.TagRegistry

class ParseTLVUseCase {

    fun execute(tlvData: String): List<TLV> {
        val tlvList = mutableListOf<TLV>()
        var index = 0

        while (index < tlvData.length) {
            // Extract tag dynamically
            val tag = extractTag(tlvData, index)
            index += tag.length

            // Extract length in bytes
            val lengthHex = tlvData.substring(index, index + 2).replace(" ", "")
            val lengthInBytes = Integer.parseInt(lengthHex, 16)
            index += 2

            // Extract value
            val value = tlvData.substring(index, index + lengthInBytes * 2).replace(" ", "")
            index += lengthInBytes * 2

            // Add to list
            tlvList.add(TLV(tag, lengthInBytes, value))
        }

        return tlvList
    }

    /* Function below extracts the tag from TLV data, checking lengths of 2, 4, and 6 characters.
    Returns the tag if found in the dictionary, otherwise throws an exception.
    **/
    private fun extractTag(tlvData: String, index: Int): String {
        var potentialTag = ""
        for (i in 2..6 step 2) {
            potentialTag = tlvData.uppercase().substring(index, index + i)
            if (TagRegistry.emvTags.contains(potentialTag)) {
                return potentialTag
            }
        }
        return "Unknown Tag"
    }
}
