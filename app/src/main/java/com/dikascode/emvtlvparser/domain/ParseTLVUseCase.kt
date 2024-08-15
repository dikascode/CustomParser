package com.dikascode.emvtlvparser.domain

import com.dikascode.emvtlvparser.model.TLV
import com.dikascode.emvtlvparser.model.TagRegistry

class ParseTLVUseCase {

    fun execute(tlvData: String): Result {
        // Remove all spaces from the input data to ensure consistent parsing
        val trimmedTlvData = tlvData.replace(" ", "")
        val tlvList = mutableListOf<TLV>()
        var index = 0

        try {
            while (index < trimmedTlvData.length) {
                // Extract tag based on the known list of tags
                val tag = extractTag(trimmedTlvData, index)
                if (tag == "Unknown Tag") {
                    return Result.Error("Unknown tag encountered")
                }
                index += tag.length

                // Return defined error if there's no more characters after the tag to avoid an exception being thrown
                if (index + 2 > trimmedTlvData.length) {
                    return Result.Error("Error occurred parsing Tag $tag: Not enough data")
                }

                // Extract length (next 2 characters after the tag) as a single-byte length
                val lengthHex = trimmedTlvData.substring(index, index + 2)
                val lengthInBytes = Integer.parseInt(lengthHex, 16)
                index += 2

                // Calculate the value's end index
                val expectedValueLength = lengthInBytes * 2
                val valueEndIndex = index + expectedValueLength

                // Confirm there's enough characters left to extract the value else return defined error
                if (valueEndIndex > trimmedTlvData.length) {
                    return Result.Error("Error occurred parsing Tag $tag: Not enough data for value field")
                }

                // Extract value
                val value = trimmedTlvData.substring(index, valueEndIndex)
                index = valueEndIndex

                tlvList.add(TLV(tag, lengthInBytes, value))
            }

            return Result.Success(tlvList)
        } catch (e: Exception) {
            return Result.Error("Failed to parse TLV data: Not enough data")
        }
    }

    private fun extractTag(tlvData: String, index: Int): String {
        // Iterate over possible tag lengths to find first valid tag
        for (tagLength in 2..6 step 2) {
            // Ensure there's enough characters left in the string to extract the tag
            if (index + tagLength <= tlvData.length) {
                val potentialTag = tlvData.substring(index, index + tagLength)
                if (TagRegistry.isKnownTagRetrieved(potentialTag)) {
                    return potentialTag
                }
            }
        }
        return "Unknown Tag"
    }

}
